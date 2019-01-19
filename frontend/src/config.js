/*define the current endpoint used on the API at url defined.
*/
var URL = "http://localhost:8080/lounge";
var BACKEND = { 
    url    : URL,
    login  : `${URL}/login`, 
    logout : `${URL}/logout`, 
    lounge : `${URL}/lounge`
}

module.exports = {
    BACKEND
};