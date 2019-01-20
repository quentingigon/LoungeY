import Cookies from 'universal-cookie';
const cookies = new Cookies();

const { BACKEND } = require('./config.js'); 

export function queryBackend( jsonBody, responseHandle ) {
    fetch(BACKEND.login, {
        method: "POST",
        mode:"cors",
        credentials: "omit",    // include, *same-origin, omit
        cache:"no-cache",
        headers: {
          'Authorization': `Bearer ${cookies.get('token')}`,
          'Content-Type': 'application/json',
        },
      
        body: jsonBody
      })
      .then( (response) => {
        console.log( response.headers.get('Authorization'));
    
          if (response.status >= 200 && response.status < 300) {
            
            responseHandle(response);
            
           
  
          } else {
            return Promise.reject(new Error(response.statusText))
          }
})    .catch(error => console.log(error) );

  
};