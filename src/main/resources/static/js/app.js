function App (){
    var that = this;

    // Constant value for board size.
    var BOARD_SIZE = 4;

    function _randChar(){
        var alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet[Math.floor(Math.random() * alphabet.length)];
    }

    // Helper for returning columns.
    function _createColumns(size){
        var arr = [];
        for(var i = 0; i < size; i++){
            arr.push({
                value: ko.observable(_randChar()),
                selected: ko.observable(false)
            })
        }
        return arr;
    }

    // Helper for returning rows.
    function _createRows(size){
        var arr = [];
        for(var i = 0; i < size; i++){
            arr.push({cols: ko.observableArray(_createColumns(size))});
        }
        return arr;
    }

    // View model.
    that.rows = ko.observableArray(_createRows(BOARD_SIZE));
    that.results = ko.observableArray();

    // Solve function.
    that.solve = function(){

        // Assemble characters.
        var characters = [];
        $.each(that.rows(), function(ri, row){
            characters.push([]);
            $.each(row.cols(), function(ci, col){
                characters[ri].push(col.value());
            });
        });

        // Make server side call to solve the puzzle.
        $.ajax({
            method: "POST",
            contentType: "application/json",
            dataType: "json",
            url: "/rest/v1/solve",
            data: JSON.stringify({
                width: BOARD_SIZE,
                height: BOARD_SIZE,
                characters: characters
            })
        }).done( function (data) {

            that.results(data.wordsList.map(function (d) {

                var thisd = d;
                // Add function to change the state of the highlighting on the board.
                thisd.selectMe = function () {

                    // Reset all highlighting.
                    for (var i = 0; i < BOARD_SIZE; i++) {
                        for (var j = 0; j < BOARD_SIZE; j++) {
                            that.rows()[i].cols()[j].selected(false);
                        }
                    }

                    // Change state based on path.
                    $.each(thisd.path, function (i, v) {
                        that.rows()[v.y].cols()[v.x].selected(true);
                    });

                };
                return thisd;

            }));
        })

    };

    return that;
}