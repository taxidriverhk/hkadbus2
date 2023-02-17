import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import {
  Button,
  Glyphicon
} from 'react-bootstrap';

import { photoCardType } from 'photo/types';
import styles from 'photo/style/style.scss';

const colorBsStyleMap = {
  'red': 'danger',
  'green': 'success',
  'yellow': 'warning',
  'light_blue': 'info',
  'blue': 'info'
};

const defaultProps = {
  card: {
    caption: '',
    description: null,
    extra: '',
    link: '',
    backgroundColor: ''
  },
  width: '100%'
};
const propTypes = {
  card: photoCardType.isRequired,
  width: PropTypes.string
};

export default function PhotoCard({
  card,
  width
}) {
  return (
    <Button
      className={styles['photo-card']}
      bsStyle={colorBsStyleMap[card.backgroundColor] || 'default'}
      componentClass={Link}
      href={card.link}
      to={card.link}
      style={{
        width: width
      }}
    >
      <img src={card.photo} />
      <div className={styles['photo-card-caption']}>
        <strong>{card.caption}</strong>
      </div>
      {card.description}
      {
        card.extra &&
          <div>
            <Glyphicon glyph="camera" /> {card.extra}
          </div>
      }
    </Button>
  );
}

PhotoCard.defaultProps = defaultProps;
PhotoCard.propTypes = propTypes;
