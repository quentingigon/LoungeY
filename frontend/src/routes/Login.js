import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import FormLogin from '../components/FormLogin';
import Cookies from 'universal-cookie';
const cookies = new Cookies();


const { BACKEND } = require('../config.js'); 

const styles = theme => ({
  root: {
    padding: '120px 16px 16px',
  },
  form: {
    width: '100%',
    maxWidth: 360,
    margin: '0 auto',
  },
});

const PageLogin = ({ classes, history }) => {
  const handleSubmit = (values) => {
    console.log('submitting formValues', values);
    history.push('/login');
    console.log(values.username);
    
    if(values.username !== ""){
    fetch(BACKEND.login, {
      method: "POST",
      mode:"cors",
      credentials: "omit", // include, *same-origin, omit
      cache:"no-cache",
      headers: {
        'Authorization': 'Bearer TOKEN',
        'Content-Type': 'application/json',
        'Origin': ''
      },
    
      body: JSON.stringify({ 
        username: values.username,
        password: values.password
      
      })
    })
    .then( (response) => {
      console.log( response.headers.get('Authorization'));

        if (response.status >= 200 && response.status < 300) {
     
          cookies.set('token', response.headers.get('Content-Language'), { path: '/' });
          cookies.set('username', values.username, { path: '/' });

          console.log("here token:");
          console.log(cookies.get('token')); 
          console.log(cookies.get('username')); 
          //redirect to profile page after login
          history.push('/profile/'+cookies.get('username'));

        } else {
          return Promise.reject(new Error(response.statusText))
        }
      

//      document.cookie = `jwt_token=${response}; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/`;

 //     document.cookie = response.headers.get('Content-Language');
      
 //     console.log(document.cookie);

    })
    .catch(error => console.log(error) );
    
  }
  };

  return (
    <div className={classes.root}>
      <FormLogin
        className={classes.form}
        onSubmit={handleSubmit}
      />
    </div>
  );
};

PageLogin.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
  history: PropTypes.object,
};

export default withStyles(styles)(PageLogin);
