import React from 'react';
import { Link } from 'react-router-dom';

import {
  Button,
  Image
} from 'react-bootstrap';

import styles from 'photo/style/style.scss';
import { segmentType } from 'photo/types';

const propTypes = {
  segment: segmentType.isRequired
};

export default function PhotoSegment({
  segment: {
    photo,
    title,
    link
  }
}) {
  return (
    <Button
      className={styles['bus-model-item-btn']}
      componentClass={Link}
      href={link}
      to={link}
    >
      <div className={styles['bus-model-item']}>
        <div>
          <Image
            responsive
            size="small"
            src={photo}
          />
        </div>
        <div>
          {title}
        </div>
      </div>
    </Button>
  );
}

PhotoSegment.propTypes = propTypes;
