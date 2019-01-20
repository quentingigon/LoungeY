import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';

import AppBar from '../components/AppBar';
import ProfileHeader from '../components/ProfileHeader';
import PostCard from '../components/PostCard';
import FormPost from '../components/FormPost';
import Cookies from 'universal-cookie';
const cookies = new Cookies();


const { queryBackend } = require('../connect.js');
const styles = theme => ({
  root: {},
  main: {
    width: '100%',
    maxWidth: theme.layout.contentMaxWidth,
    margin: '0 auto',
    padding: 24,
  },
  header: {
    marginBottom: 24,
  }
});

console.log(`logt ${window.location.href}`);
let i = 0;

const PageProfile = ({ classes, history }) => {

  const handleSubmit = (values) => {
    console.log('submitting post', values);
 //   history.push('/profile');
    console.log(values);
    let jsonBody = JSON.stringify({
      username: cookies.get('username'),
      post: {
        date:'',
        username: cookies.get('username'),
        text:values.corpus, 
        type:values.postType,
        isCorrectAnswer: false, //never when post is new
        isPublic: values.isPublic,
        hastags:[
          "ON","SAMUSE","QUENTIN","CEST_LA_TEUF"
        ]

      }
    })
    queryBackend(jsonBody, (response)=>{
        
    });
  }
  return (
  <div className={classes.root}>
    <AppBar />
    <div className={classes.main}>
      <ProfileHeader
        className={classes.header}
        displayName="Brandon Folks"
        bio="Professional photographer"
        coverUrl="https://source.unsplash.com/collection/841904"
        avatarUrl="https://source.unsplash.com/collection/895539"
        stats={{
          posts: 112,
          followers: 234,
          following: 22,
        }}
      />

      <Grid container spacing={24}>
      <Grid item xs={24} sm={12} md={24}>
      <FormPost
        className={classes.form}
        onSubmit={handleSubmit}
        ></FormPost>

        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <PostCard
            title="Spicy Carrot Salad"
            subtitle="@Anna posted 1 hour ago"
            imageUrl="https://source.unsplash.com/L1ZhjK-R6uc/1600x900"
            avatarUrl="https://source.unsplash.com/b1Hg7QI-zcc/150x150"
            body="Because this salad is so simple, fresh, top-quality tomatoes and mozzarella are important"
          />
        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <PostCard
            title="Burrata Black Bean Burgers"
            subtitle="@Sandra posted 3 days ago"
            imageUrl="https://source.unsplash.com/sWq83ZbZb6U/1600x900"
            avatarUrl="https://source.unsplash.com/EGVccebWodM/150x150"
            body="These vegetarian burgers are delicious! Your carnivorous friends will be impressed. My favorite way to serve is on a whole-wheat..."
          />
        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <PostCard
            title="Vegan Shepherd's Pie"
            subtitle="@Janne posted 1 week ago"
            imageUrl="https://source.unsplash.com/l_DY1GYtjTo/1600x900"
            avatarUrl="https://source.unsplash.com/yl2rJVuNWFQ/150x150"
            body="Looks yummy, but not very healthy at all. I'll try leaving out the vegan mayo and cream cheese. I think I might try it with soaked..."
          />
        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <PostCard
            title="Rice cake eggs"
            subtitle="@James posted 2 weeks ago"
            imageUrl="https://source.unsplash.com/kZeUekYF9Jw/1600x900"
            avatarUrl="https://source.unsplash.com/d2MSDujJl2g/150x150"
            body="When you've got the whole gang along for the camping trip, make breakfast eggs the easy way and enjoy a slow sip of your coffee..."
          />
        </Grid>
      </Grid>
    </div>
  </div>
  );
};

PageProfile.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
};

export default withStyles(styles)(PageProfile);
