import Cookies from 'universal-cookie';
const cookies = new Cookies();

const { BACKEND } = require('./config.js'); 

export function queryBackend( jsonBody, responseHandle ) {
    fetch(BACKEND.posts, {
        method: "POST",
        mode:"cors",
        credentials: "omit",    // include, *same-origin, omit
        cache:"no-cache",
        headers: {
          'Authorization': `Bearer ${cookies.get('token')}`,
          'Content-Type': 'application/json',
          'Cache-Control': 'no-cache'
        },
      
        body: `${jsonBody}`
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