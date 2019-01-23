import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import CardActions from '@material-ui/core/CardActions';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import FavoriteIcon from '@material-ui/icons/Favorite';
import CommentIcon from '@material-ui/icons/Comment'
import Avatar from '@material-ui/core/Avatar';

const styles = theme => ({
  media: {
    height: 0,
    paddingTop: '56.25%', // 16:9
  },
  commentButton: {
    marginTop: 24,
//    width:'30%'
    float:'right'
  }
});

const PostCard = ({ classes, className, children, title, subtitle, imageUrl, author, avatarUrl, body }) => {
  
  const cardMedia = () =>{
    if(imageUrl === "" || imageUrl == " " || imageUrl == null){
      return( <div></div>);
    }else{
      return(
        <CardMedia
        className={classes.media}
        image={imageUrl}
        title={title}
      />
      )
    }
  };
 
  return(
  <Card className={className}>
    <CardHeader
      avatar={<Avatar src={avatarUrl}>{author} </Avatar>}
      title={title}
      subheader={subtitle}
    />
    {cardMedia()}
    <CardContent>
      <Typography component="p">
        {body}
      </Typography>
    </CardContent>
    <CardActions>
     

      <IconButton className={classes.commentButton}
        color="secondary"
      >
        <CommentIcon />
      </IconButton>
    </CardActions>
  </Card>)
};

PostCard.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
  className: PropTypes.string,
  children: PropTypes.node,
  title: PropTypes.string,
  subtitle: PropTypes.string,
  imageUrl: PropTypes.string,
  avatarUrl: PropTypes.string,
  body: PropTypes.string,
};

export default withStyles(styles)(PostCard);
