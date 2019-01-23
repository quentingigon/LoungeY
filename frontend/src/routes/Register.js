import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import FormSignUp from '../components/FormSignUp';

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

const PageSignUp = ({ classes, history }) => {
  const handleSubmit = (values) => {
      console.log('submitting formValues', values);
      history.push('/register');
      console.log(BACKEND.url);


      //sanitarization values and user feedback

      fetch(BACKEND.register, {
          method: "post",
          mode:"cors",
          //  credentials: "include", // include, *same-origin, omit
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          },

          body: JSON.stringify({
              username: values.username,
              name: values.name,
              email: values.email,
              password: values.password,
              isAdmin: values.isAdmin,
              year: values.year,
              orientation: values.orientation, 
              favBeer: values.favBeer
          })
      })
          .then( (response) => {
              console.log(response);
              if (response.status >= 200 && response.status < 300) {
                history.push('/login');

              }
              document.cookie = `jwt_token=${response}; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/`;
              console.log(document.cookie);
              //do something awesome that makes the world a better place
          });
  };

  return (
    <div className={classes.root}>
      <FormSignUp
        className={classes.form}
        onSubmit={handleSubmit}
      />
    </div>
  );
};

PageSignUp.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
  history: PropTypes.object,
};

export default withStyles(styles)(PageSignUp);
