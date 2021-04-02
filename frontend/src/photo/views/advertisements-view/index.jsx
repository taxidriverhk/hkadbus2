import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import { Breadcrumb } from 'react-bootstrap';

import { strings } from 'shared/store/localization';
import ErrorMessageBox from 'shared/components/error-message-box';
import Loader from 'shared/components/loader';
import PhotoCardList from 'photo/components/photo-card-list';

const propTypes = {
  advertisements: {},
  category: {},
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.string.isRequired
};

export default function AdvertisementsView({
  advertisements,
  category,
  isFetching,
  error
}) {
  if (isFetching) {
    return <Loader />;
  } else if (error || !category) {
    return <ErrorMessageBox error={error} />;
  } else {
    const advertisementCards = advertisements.map(advertisement => {
      return {
        photo: advertisement.thumbnail,
        caption: advertisement.name,
        link: `/search?advertisementId=${advertisement.id}`,
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
            to="/categories"
            href="/categories"
          >
            {strings.categories}
          </Breadcrumb.Item>
          <Breadcrumb.Item active>
            {category.name}
          </Breadcrumb.Item>
        </Breadcrumb>
        <PhotoCardList cards={advertisementCards} />
      </div>
    );
  }
}

AdvertisementsView.propTypes = propTypes;
