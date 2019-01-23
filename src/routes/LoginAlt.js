import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import FormLogin from '../components/FormLogin';
import Cookies from 'universal-cookie';

const { queryBackend }  = require('../connect.js'); 

const { BACKEND } = require('../config.js'); 

const cookies = new Cookies();

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

const stockCookies = ({response, values, history, cookies}) => {
  cookies.set('token', response.headers.get('Content-Language'), { path: '/' });
  cookies.set('username', values.username, { path: '/' });

  console.log("here token:");
  console.log(cookies.get('token')); 
  console.log(cookies.get('username')); 
  //redirect to profile page after login
  history.push('/profile/'+cookies.get('username'));
}

const PageLogin = ({ classes, history }) => {
  const handleSubmit = (values) => {
    console.log('submitting formValues', values);
    history.push('/login');
    console.log(values.username);
    
    if(values.username !== ""){

      let   jsonBody = JSON.stringify({ 
        username: values.username,
        password: values.password
      
      });

      queryBackend( jsonBody, function(){ stockCookies(values, history, cookies)})
   

//      document.cookie = `jwt_token=${response}; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/`;

 //     document.cookie = response.headers.get('Content-Language');
      
 //     console.log(document.cookie);

    
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
