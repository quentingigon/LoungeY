import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';

import AppBar from '../components/AppBar';
import ProfileHeader from '../components/ProfileHeader';
import PostCard from '../components/PostCard';
import FormPost from '../components/FormPost';

import { useState, useEffect } from 'react';

import Cookies from 'universal-cookie';
import FormSignUp from '../components/FormSignUp';
const cookies = new Cookies();


const { BACKEND } = require('../config.js'); 
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


const UserSettings = ({ classes, history, state, location}) => {
  if(cookies.get('token') == null){
    history.push("/login")
  }

  let PAGIN = 200;
  const [data, setData] = useState(0);
  const [ListPost, setListPost] = useState([]);
  const [UserProfile, setUserProfile] = useState([]);


  useEffect(() => {
    fetchUserProfile();
  }, []);

  // Modern syntax >= React 16.2.0

  const fetchedPosts = () => {
    const ListPosts = ({Posts}) => (
      <>
    {ListPost.map(post => (
      <Grid item xs={12} sm={6} md={4}>
      <PostCard
          title={post.username} /*{listPost[0].title}*/
          subtitle={post.date}
          imageUrl={post.contentUrl}
          avatarUrl="https://media.giphy.com/media/3NtY188QaxDdC/giphy.gif"
          body={post.text}
          author={post.username.substring(0,2).toUpperCase()}
        />
        
   
        </Grid>
    ))}
      </>
    ); 

    /*WIP
         <hr/>
         <FormPost
        className={classes.form}
        onSubmit={handleSubmit}
        display="none"
        ></FormPost>
        */
       
    return ( 
      <ListPosts Posts={ListPost}></ListPosts>
  
      ); 
  }

  const fetchedUserProfile = () => {

    let username = UserProfile.username != null ? UserProfile.username : "loading ...";

    //data validation
    let nbPost = UserProfile.posts != null ? UserProfile.posts.length : 0;
    let ori = UserProfile.orientation != null ? UserProfile.orientation : "?";
    let year = UserProfile.yearOfStudy != null ? UserProfile.yearOfStudy : "1";
    let beer = UserProfile.favBeer != null ? UserProfile.favBeer : "none yet";

    return (
      <ProfileHeader
      className={classes.header}
      displayName={username}
      bio={beer}
      coverUrl={UserProfile.coverURL}
      avatarUrl={UserProfile.profilePicURL}
      stats={{
        posts: nbPost,
        year: year,
        orientation: ori,
      }}
    />
    )
  }

  const handleSubmit = (values) => {
    console.log('submitting post', values);

    /*sanitarization*/
    let corpus = String(values.corpus);
    let postType = "";
    if(values.rootPostId != null){
      postType = "COMMENT";
    }else{
       postType = "POST";
    }
    //generate type of post
    if(corpus.indexOf("?") != -1 ){
      postType = "QUESTION";
    }

    //generate hashtags list
    let cur = corpus.indexOf("#", 0);
    let hashtagList = [];
    let i = 0;
  while( cur != -1 && i < corpus.length){
  let endHashTag = corpus.indexOf(" ", cur);
      endHashTag = endHashTag == -1 ? corpus.length : endHashTag;

      hashtagList.push( 
        corpus.substring(cur+1, endHashTag).toUpperCase() 
      );
      i++;
      cur = corpus.indexOf("#", cur+1);
      }
    /*---------------------*/

    //create body for POST request
    let jsonBody = JSON.stringify({
      username: cookies.get('username'),
      post: {
        date:'',
        username:         cookies.get('username'),
        text:             values.corpus, 
        type:             postType,//values.postType,
        isCorrectAnswer:  false, //never when post is new
        isPublic:         values.isPublic,
        responses:        [ null ],
        hashtags:         hashtagList,
        contentUrl:""
        //  "ON","SAMUSE","QUENTIN","CEST_LA_TEUF"

      }
    })

    //query to add a post
    queryBackend(BACKEND.posts, jsonBody, (response)=>{
        console.log("POST ADDED 2");
        console.log(response);
    } );


  }





  const fetchUserProfile = () => {
    console.log("fetch user start")
    let listPost = ["empty"];
    queryBackend(BACKEND.wall+ location.pathname.match(/([^\/]*)\/*$/)[1]+"?currentUsername="+cookies.get('username'), 
      "",  async function(response){
        let resp =  await response.json();
     //   response.then(console.log("well, ?"+response.body));
        console.log("USER FETCHED");
        console.log(resp);
        return await resp;
      //  gen
   //     console.log(JSON.stringify(response));

    }, "GET")
    .then( (response) => {
    console.log(response)
    setUserProfile(response);

    });
  
  }
  

  return (
    
  <div className={classes.root}>

    <AppBar />
    <div className={classes.main}>
      {fetchedUserProfile()}
  
      <Grid container spacing={24}>
      <Grid item xs={8} sm={8} md={8}>
      <FormSignUp
        className={classes.form}
        onSubmit={handleSubmit}
        header="Modify your infos"
        ></FormSignUp>

        </Grid>

   

      </Grid>
    </div>
  </div>
  );
};

UserSettings.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
};

export default withStyles(styles)(UserSettings);
