import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import { Breadcrumb } from 'react-bootstrap';

import { strings } from 'shared/store/localization';
import ErrorMessageBox from 'shared/components/error-message-box';
import Loader from 'shared/components/loader';
import PhotoDetails, { photoPropTypes } from 'photo/components/photo-details';

export const idNamePropTypes = {
  id: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired
};

const propTypes = {
  photo: PropTypes.shape(photoPropTypes).isRequired,
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.string.isRequired
};

export default function PhotoDetailsView({
  photo,
  isFetching,
  error
}) {
  if (isFetching) {
    return <Loader />;
  } else if (error || !photo) {
    return <ErrorMessageBox />;
  } else {
    const {
      advertisement,
      advertisementId,
    } = photo;

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
          { /* TODO: fix the category */ }
          <Breadcrumb.Item
            componentClass={Link}
            to={`/categories/fix-me`}
            href={`/categories/fix-me`}
          >
            Fix Me
          </Breadcrumb.Item>
          <Breadcrumb.Item
            componentClass={Link}
            to={`/advertisements/${advertisementId}`}
            href={`/advertisements/${advertisementId}`}
          >
            {advertisement}
          </Breadcrumb.Item>
          <Breadcrumb.Item active>
            {strings.photoDetails}
          </Breadcrumb.Item>
        </Breadcrumb>
        <PhotoDetails photo={photo} />
      </div>
    );
  }
}

PhotoDetailsView.propTypes = propTypes;
