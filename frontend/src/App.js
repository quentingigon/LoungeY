import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import Lounge from './routes/Lounge';
import PageLogin from './routes/Login';
import PageProfile from './routes/Profile';
import Register from './routes/Register';
import Joyride from 'react-joyride';


const styles = theme => ({
  root: {},
  app: {
    background: theme.palette.background.paper,
  }
});
class App extends Component {


  constructor (props) {
    super(props);

    this.state = {
    steps: [
      {
        target: '.onBoardStep1',
        content: 'This is my awesome feature!',
      },
      {
        target: '.my-other-step',
        content: 'This another awesome feature!',
      },
    ]
  };
}
  render() {
  
    const { steps } = this.state;

    return (
      <div id="app">
        <Joyride
          steps={steps}
        />
      <Switch>
        <Route path="/lounge"  component={Lounge}      
         render={props => <Lounge {...props} stateX={this.state} />}
        />
        <Route path="/login" component={PageLogin} />
        <Route path="/register" component={Register} />
        <Route path="/profile/:username" component={PageProfile} 
        />
      </Switch>
      </div>
    );
  }
}


export default App;
