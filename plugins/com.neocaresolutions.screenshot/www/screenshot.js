var exec = require('cordova/exec');
/**
 * Constructor
 */
function Screenshot() {}

Screenshot.prototype.saveScreenshot = function() {
  exec(function(result){
      // result handler
      alert(result);
    },
    function(error){
      // error handler
      alert("Error" + error);
    }, 
    "Screenshot",
    "saveScreenshot",
    []
  );
}

var screenshot = new Screenshot();
module.exports = screenshot;