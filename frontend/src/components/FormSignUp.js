import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import CardContent from '@material-ui/core/CardContent';

import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';

const styles = theme => ({
  form: {
    display: 'flex',
    flexDirection: 'column',
  },
  formControl: {
    margin: theme.spacing.unit * 3,
  },
  group: {
    margin: `${theme.spacing.unit}px 0`,
  },
  submitButton: {
    marginTop: 24,
  }
});

const FormSignUp = ({ classes, className, onSubmit }) => {

  const [values, setValues] = useState({
    username: '',
    name:'',
    email: '',
    password: '',
    passwordConfirm: '',
    orientation:'',
    year:'',
    favBeer:''
  });

  const handleChange = key => e => {
    setValues({ ...values, [key]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(values);
  };

  return (
    <Paper className={className}>
      <CardContent>
        <Typography variant="h4" align="center">Register</Typography>
        <form className={classes.form} onSubmit={handleSubmit}>
          <TextField
            type="text"
            label="Email"
            margin="normal"
            value={values.email}
            onChange={handleChange('email')}
            required
          />
          <TextField
            type="text"
            label="Username"
            margin="normal"
            value={values.username}
            onChange={handleChange('username')}
            required
          />
          <TextField
            type="password"
            label="Password"
            margin="normal"
            value={values.password}
            onChange={handleChange('password')}
            required
          />
          <TextField
            type="password"
            label="Confirm password"
            margin="normal"
            value={values.passwordConfirm}
            onChange={handleChange('passwordConfirm')}
            required
          />
        <FormControl component="fieldset" className={classes.formControl}>
        <FormLabel component="legend">Current year of studies</FormLabel>
        <RadioGroup
          aria-label="Gender"
          name="gender1"
          className={classes.group}
          value={values.year}
          onChange={handleChange('year')}
        >
          <FormControlLabel value="1" control={<Radio />} label="First Year" />
          <FormControlLabel value="2" control={<Radio />} label="Second Year" />
          <FormControlLabel value="3" control={<Radio />} label="Third Year" />
          <FormControlLabel
            value="disabled"
            disabled
            control={<Radio />}
            label="(Disabled option)"
          />
        </RadioGroup>
        </FormControl>

          <TextField
            type="text"
            label="Orientation"
            margin="normal"
            value={values.orientation}
            onChange={handleChange('orientation')}
            required
          />
          <Button
            className={classes.submitButton}
            variant="contained"
            type="submit"
            color="primary"
          >
            Register
          </Button>
        </form>
      </CardContent>
    </Paper>
  );
};

FormSignUp.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
  className: PropTypes.string,
  onSubmit: PropTypes.func,
};

FormSignUp.defaultProps = {
  onSubmit: () => { },
};

export default withStyles(styles)(FormSignUp);
