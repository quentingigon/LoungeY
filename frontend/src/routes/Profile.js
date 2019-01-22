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
import TablePaginationActions from '@material-ui/core/TablePagination/TablePaginationActions';
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

console.log(`logt ${window.location.href}`);
let i = 0;
console.log(this);

const PageProfile = ({ classes, history, state }) => {

  const [data, setData] = useState(0);
  const [ListPost, setListPost] = useState([]);

  useEffect(() => {
    fetchPost();
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
          imageUrl=""
          avatarUrl="https://source.unsplash.com/b1Hg7QI-zcc/150x150"
          body={post.text}
        />
        </Grid>
    ))}
      </>
    ); 
    let post = JSON.stringify(ListPost[0]);
    for (let i = 0; i < ListPost.length; i++) {
      const element = ListPost[i];

      console.log(element["id"]);
      
 
    }
    return ( 
      <ListPosts Posts={ListPost}></ListPosts>
  
      ); 
  }
  console.log("test he");
  let tmp = JSON.stringify(ListPost[4]);
  console.log(tmp);
  
  console.log("test ");
  const handleSubmit = (values) => {
    console.log('submitting post', values);

    /*sanitarization*/
    let corpus = String(values.corpus);
    let postType = "POST";
    //generate type of post
    if(corpus.indexOf("?") != -1 ){
      postType = "QUESTION";
    }

    //generate hashtags list
    let cur = corpus.indexOf("#", cur+1);
    let hashtagList = [];
    let i = 0;
    do{
      let endHashTag = corpus.indexOf(" ", cur);
      endHashTag = endHashTag == -1 ? corpus.length : endHashTag;

      hashtagList.push( 
        corpus.substring(cur+1, endHashTag).toUpperCase() 
      );
      i++;
      cur = corpus.indexOf("#", cur+1);

    }while( cur != -1 && i < corpus.length);

    console.log("hashtag list");
    console.log(hashtagList);
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
        hashtags:         hashtagList
        //  "ON","SAMUSE","QUENTIN","CEST_LA_TEUF"

      }
    })

    //query to add a post
    queryBackend(BACKEND.posts, jsonBody, (response)=>{
        console.log("POST ADDED 2");
        console.log(response);
        fetchPost();
    } );


  }
  console.log("pvalid");

  console.log(data);
  function fetchPost() {
    console.log("fetch start")
    let listPost = ["empty"];
    listPost = queryBackend(BACKEND.profile+cookies.get('username'), 
      "",  async function(response){
        let resp =  await response.json();
     //   response.then(console.log("well, ?"+response.body));
        console.log("POST FETCHED");
        console.log(resp);
        return await resp;
      //  gen
   //     console.log(JSON.stringify(response));

    }, "GET");
  console.log("listPost: ");
  listPost.then( (response) => {
    console.log(response)
 //   setData(response.id);
    setListPost(response);
  //  history.push("profile"+cookies.get("username"));
    state = response;
    console.log(state);
  });
    console.log("SADDS")
    console.log(ListPost)
    console.log("SADDS")




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
      <Grid item xs={8} sm={8} md={8}>
      <FormPost
        className={classes.form}
        onSubmit={handleSubmit}
        ></FormPost>

        </Grid>
        {fetchedPosts()}

        <Grid item xs={12} sm={6} md={4}>
          <PostCard
            title="Spicy Carrot Salad"
            subtitle="@Anna posted 1 hour ago"
            imageUrl="https://source.unsplash.com/L1ZhjK-R6uc/1600x900"
            avatarUrl="https://source.unsplash.com/b1Hg7QI-zcc/150x150"
            body="Because this salad is so simple, fresh, top-quality tomatoes and mozzarella are important"
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
