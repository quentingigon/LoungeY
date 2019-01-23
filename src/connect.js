import Cookies from 'universal-cookie';
const cookies = new Cookies();

export function queryBackend(url ,jsonBody, responseHandle , meth = "POST") {
  return fetch(url, {
        method: meth,
        mode:"cors",
        credentials: "omit",    // include, *same-origin, omit
        cache:"no-cache",
        headers: {
          'Authorization': `Bearer ${cookies.get('token')}`,
          'Content-Type': 'application/json',
          'Cache-Control': 'no-cache',
          'Accept':'application/json'
        },
      
        body: meth == "POST" ? `${jsonBody}` : null,
      })
      .then( (response) => {
        console.log( "AUTH: "+response.headers.get('Authorization'));
    
          if (response.status >= 200 && response.status < 300) {
            
            console.log("We pass this:\n"+response);
            return  Promise.resolve(responseHandle(response));
            
           
  
          } else {
            return Promise.reject(new Error(response.statusText))
          }
})    .catch(error => console.log(error) );

  
};