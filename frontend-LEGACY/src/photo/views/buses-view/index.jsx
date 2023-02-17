import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import { Breadcrumb } from 'react-bootstrap';

import { strings } from 'shared/store/localization';
import ErrorMessageBox from 'shared/components/error-message-box';
import Loader from 'shared/components/loader';
import PhotoCardList from 'photo/components/photo-card-list';

const propTypes = {
  buses: PropTypes.arrayOf(PropTypes.shape({})).isRequired,
  busModel: PropTypes.shape({}).isRequired,
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.string.isRequired
};

export default function BusesView({
  buses,
  busModel,
  isFetching,
  error
}) {
  if (isFetching) {
    return <Loader />;
  } else if (error || !busModel) {
    return <ErrorMessageBox />;
  } else {
    const busCards = buses.map(bus => {
      return {
        photo: bus.thumbnail,
        caption: ``,
        link: `/buses/${bus.id}`,
      };
    });
    return (
      <div>
        <Breadcrumb>
          <Breadcrumb.Item
            componentClass={Link}
            to="/"
            href="/"
          >
            {strings.home}
          </Breadcrumb.Item>
          <Breadcrumb.Item
            componentClass={Link}
            to="/models"
            href="/models"
          >
            {strings.models}
          </Breadcrumb.Item>
          <Breadcrumb.Item active>
            {busModel.name}
          </Breadcrumb.Item>
        </Breadcrumb>
        <PhotoCardList cards={busCards} />
      </div>
    );
  }
}

BusesView.propTypes = propTypes;
