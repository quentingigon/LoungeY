import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import CardContent from '@material-ui/core/CardContent';
import Cookies from 'universal-cookie';
import Switch from '@material-ui/core/Switch';
import Grid from '@material-ui/core/Grid';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import AddPostIcon from '@material-ui/icons/AddComment'



const cookies = new Cookies();

const styles = theme => ({
  form: {
    display: 'flex',
    flexDirection: 'column',
  },
  submitButton: {
    marginTop: 24,
//    width:'30%'
    float:'right'
  }
});

  

const FormPost = ({ classes, className, onSubmit }) => {

  const [corpus, setcorpus] = useState('');
  const [postType, setpostType] = useState('');
  const [isPublic, setisPublic] = useState('');

  const [state, setState] = React.useState({
    checkedA: true,
    labelText: 'only your friends'
  });

  const handleChange = name => event => {

    let textLabel = event.target.checked ? 'only your friends' : 'everybody';

      console.log(event.target.checked);
      console.log(textLabel);
    setState({ ...state, 
        [name]: event.target.checked, 
        labelText: textLabel,
        isPublic:event.target.checked });

 
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ corpus, postType, isPublic });
  };

  return (
    <Paper className={className}>
      <CardContent>
        <Typography variant="h5" align="left">Talk to </Typography>
        <form className={classes.form} onSubmit={handleSubmit}>
          <TextField
            id="standard-multiline-static"
            multiline
            rows="4"
            className={classes.textField}
            margin="normal"
            variant="outlined"
            type="text"
            label={state.labelText}
            margin="normal"
            value={corpus}
            onChange={(e) => setcorpus(e.target.value)}
          />
           
        <Grid container spacing={12}>
            <Grid item xs={16} sm={8} md={8}>
            <FormControlLabel
                control={
        
                    <Switch checked={state.checkedA} 
                    onChange={handleChange('checkedA')} 
                    value="checkedA" 
                        />
                        }
                label="Private mode"

            />
            </Grid>
            <Grid item xs={8} sm={4} md={4}>
                <Button
                    className={classes.submitButton}
                    variant="text"
                    type="submit"
                    color="secondary"
                >
                     <AddPostIcon />
                      Post

                </Button>
                </Grid>
        </Grid>

        </form>
      </CardContent>
    </Paper>
  );
};

FormPost.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
  className: PropTypes.string,
  onSubmit: PropTypes.func,
};

FormPost.defaultProps = {
  onSubmit: () => { },
};

export default withStyles(styles)(FormPost);
