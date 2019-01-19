import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import FormLogin from '../components/FormLogin';

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
    console.log(BACKEND.url);
    
    fetch(BACKEND.login, {
      method: "post",
      mode:"cors",
    //  credentials: "include", // include, *same-origin, omit
      headers: {
        //'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    
      body: JSON.stringify({ 
        username: values.email,
        password: values.password
      
      })
    })
    .then( (response) => { 
      console.log(response);
      document.cookie = `jwt_token=${response}; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/`;
      console.log(document.cookie);
       //do something awesome that makes the world a better place
    });

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
