import PropTypes from 'prop-types';
import React from 'react';

import {
  ButtonToolbar
} from 'react-bootstrap';

import PhotoCard, { cardPropTypes } from 'photo/components/photo-card';

const PHOTOS_PER_ROW = 4;

const defaultProps = {
  cards: []
};
const propTypes = {
  cards: PropTypes.arrayOf(PropTypes.shape(cardPropTypes))
};

export default function PhotoCardList({ cards }) {
  const cardWith = parseInt(100/PHOTOS_PER_ROW - 1);
  return (
    <ButtonToolbar>
      {
        cards.map((card, index) => (
          <PhotoCard
            key={index}
            card={card}
            width={`${cardWith}%`}
          />
        ))
      }
    </ButtonToolbar>
  );
}

PhotoCardList.defaultProps = defaultProps;
PhotoCardList.propTypes = propTypes;