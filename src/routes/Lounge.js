import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Layout from '../components/Layout';
import PostCard from '../components/PostCard';
import Grid from '@material-ui/core/Grid';

import ProfileCard from '../components/ProfileCard';
import Cookies from 'universal-cookie';
import FormPost from '../components/FormPost';
import { useState, useEffect } from 'react';

const { BACKEND } = require('../config.js'); 
const { queryBackend } = require('../connect.js');

const cookies = new Cookies();

const styles = theme => ({
  post: {
    marginBottom: theme.spacing.unit * 2,
  }
});
console.log("Here be cookie");
console.log(cookies.get('token')); // Pacman

const Lounge = ({ classes, history, location }) => {

  const [ListPost, setListPost] = useState([]);

  useEffect(() => {
    fetchPost();
  }, []);

  const fetchPost = () => {
    console.log("fetch start")
    let listPost = ["empty"];
    console.log(location.pathname);
    listPost = queryBackend(BACKEND.lounge+
      cookies.get('username'), 
      "",  async function(response){
        let resp =  await response.json();
     //   response.then(console.log("well, ?"+response.body));
        console.log("POST FETCHED");
        console.log(resp);
        return await resp;
      //  gen
   //     console.log(JSON.stringify(response));

    }, "GET")
    .then( (response) => {
    console.log(response)
    setListPost(response);

    });
  }

  const fetchedPosts = () => {
    const ListPosts = ({Posts}) => (
      <>
    {ListPost.map(post => (
      <PostCard
      className={classes.post}
          title={post.username} /*{listPost[0].title}*/
          subtitle={post.date}
          imageUrl={post.contentUrl}
          avatarUrl="https://media.giphy.com/media/3NtY188QaxDdC/giphy.gif"
          body={post.text}
          author={post.username.substring(0,2).toUpperCase()}
        />
        
   
    ))}
      </>
    ); 
    return ( 
      <ListPosts Posts={ListPost}></ListPosts>
  
      ); 
  }

  return(

  <Layout
    >
    <div id="test">
    {fetchedPosts()}

   

    </div>

  </Layout>
)};

Lounge.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
};

export default withStyles(styles)(Lounge);
