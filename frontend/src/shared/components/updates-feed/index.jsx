import { DateTime } from 'luxon';
import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import {
  Button,
  ButtonGroup,
  ListGroup,
  ListGroupItem,
  Media
} from 'react-bootstrap';

import styles from 'shared/style/style.scss';
import * as Constants from 'shared/store/constants';
import { strings } from 'shared/store/localization';
import ErrorMessageBox from 'shared/components/error-message-box';
import Loader from 'shared/components/loader';

export const updatesPropTypes = {
  id: PropTypes.string.isRequired,
  summary: PropTypes.string.isRequired,
  time: PropTypes.string.isRequired,
  photo: PropTypes.string.isRequired
};
const propTypes = {
  error: PropTypes.string.isRequired,
  isFetching: PropTypes.bool.isRequired,
  updates: PropTypes.arrayOf(
    PropTypes.shape(updatesPropTypes)).isRequired
};

export default function UpdatesFeed({
  error,
  isFetching,
  updates
}) {
  if (isFetching) {
    return (
      <Loader />
    );
  } else if (error) {
    return (
      <ErrorMessageBox />
    );
  } else if (!updates || updates.length < 1) {
    return (
      <ListGroup>
        <ListGroupItem>{strings.noRecentUpdates}</ListGroupItem>
      </ListGroup>
    );
  }

  return (
    <ButtonGroup>
      {
        updates.map((update, index) => {
          const updateDateTime = DateTime.fromMillis(update.time);
          return (
            <Button
              className={styles['update-item']}
              componentClass={Link}
              key={index}
              href={`/updates/${update.id}`}
              to={`/updates/${update.id}`}
            >
              <Media>
                <Media.Left>
                  <img src={`${Constants.IMAGE_BASE_PATH}${update.photo}`} />
                </Media.Left>
                <Media.Body>
                  <strong>{updateDateTime.toFormat('YYYY-MM-DD HH:mm')}</strong>
                  <p>{update.summary}</p>
                </Media.Body>
              </Media>
            </Button>
          );
        })
      }
    </ButtonGroup>
  );
}

UpdatesFeed.propTypes = propTypes;