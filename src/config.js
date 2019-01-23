/*define the current endpoint used on the API at url defined.
*/
let URL = "http://localhost:8080/lounge";
//let URL = "https://gamifikator-backend.herokuapp.com/lounge";
let BACKEND = {
    url    : URL,
    login  : `${URL}/login`, 
    logout : `${URL}/logout/`, 
    lounge : `${URL}/lounge/`,
    register : `${URL}/register`,
    posts : `${URL}/posts/`,
    profile : `${URL}/lounge/`,
    users : `${URL}/users/`,
    wall : `${URL}/wall/`
}

module.exports = {
    BACKEND
};