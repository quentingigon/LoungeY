import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import cx from 'classnames';
import Avatar from '@material-ui/core/Avatar';
import Paper from '@material-ui/core/Paper';
import { Typography } from '@material-ui/core';
import ProfileStats from './ProfileStats';

import IconButton from '@material-ui/core/IconButton';
import AddPersonIcon from '@material-ui/icons/PersonAdd';

const styles = theme => ({
  root: {
    marginTop:60
  },
  header: {
    height: 10,
    background: theme.palette.common.white,
  },
  main: {
    position: 'relative',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    textAlign: 'center',
    padding: theme.spacing.unit * 3,
  },
  avatar: {
    marginTop: -100,
    height: 152,
    width: 152,
    marginBottom: theme.spacing.unit,
    border: `5px solid ${theme.palette.common.white}`,
    background: theme.palette.grey[100],
  },
  stats: {
    maxWidth: 300,
    margin: '0 auto',
  }
});

const ProfileHeader = ({ classes, displayName, 
  bio, avatarUrl, coverUrl, stats, className, onSubmit }) => {
  const headerStyle = coverUrl
    ? { backgroundImage: `url('${coverUrl}')` }
    : null;

    const handleSubmit = (e) => {
      e.preventDefault();
      onSubmit();
    };

  return (
    <Paper elevation={1} className={cx(classes.root, className)}>
      <div className={classes.header} style={headerStyle} />
      <div className={classes.main}>
        <Avatar className={classes.avatar} src={avatarUrl} >
        {displayName.substring(0,2)}
        </Avatar>
        <Typography variant="h6">{displayName}</Typography>
        <Typography variant="subtitle1" color="textSecondary">
          {"Favorite Beer: "+bio}
        </Typography>
        <IconButton variant="h2">
        <AddPersonIcon />
      </IconButton>
      </div>
      <ProfileStats
        className={classes.stats}
        posts={stats.posts}
        year={stats.year}
        orientation={stats.orientation}
      />
    </Paper>
  );
};

ProfileHeader.propTypes = {
  classes: PropTypes.objectOf(PropTypes.string),
  className: PropTypes.string,
  coverUrl: PropTypes.string,
  avatarUrl: PropTypes.string,
  bio: PropTypes.string,
  displayName: PropTypes.string,
  stats: PropTypes.shape({
    posts: PropTypes.number,
    followers: PropTypes.number,
    following: PropTypes.number,
  }),
  onSubmit: PropTypes.func,

};

export default withStyles(styles)(ProfileHeader);
