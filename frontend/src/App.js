import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import Lounge from './routes/Lounge';
import PageLogin from './routes/PageLogin';
import PageProfile from './routes/PageProfile';
import Register from './routes/Register';

class App extends Component {
  render() {
    return (
      <Switch>
        <Route path="/lounge" exact component={Lounge} />
        <Route path="/login" component={PageLogin} />
        <Route path="/register" component={Register} />
        <Route path="/profile/:username" component={PageProfile} />
      </Switch>
      
    );
  }
}


export default App;
