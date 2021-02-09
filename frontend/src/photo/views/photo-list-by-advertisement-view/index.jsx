import get from 'lodash/get';
import PropTypes from 'prop-types';
import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import { Breadcrumb, Glyphicon } from 'react-bootstrap';

import { strings } from 'shared/store/localization';
import ErrorMessageBox from 'shared/components/error-message-box';
import Loader from 'shared/components/loader';
import PhotoCardList from 'photo/components/photo-card-list';
import { BusCompanyColors, idNameType } from 'photo/types';

const propTypes = {
  photos: {},
  category: idNameType.isRequired,
  advertisement: idNameType.isRequired,
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.string.isRequired
};

export default class PhotoListByAdvertisementView extends Component {
  render() {
    const {  
      photos,
      category,
      advertisement,
      isFetching,
      error
    } = this.props;

    if (isFetching) {
      return <Loader />;
    } else if (error || !advertisement || !category) {
      return <ErrorMessageBox />;
    } else {
      const photoCards = photos.map(photo => {
        return {
          photo: photo.thumbnail,
          caption: photo.name,
          backgroundColor: get(BusCompanyColors, photo.bus.busCompanyName, ''),
          description: (
            <div>
              <Glyphicon glyph="bed" /> {photo.bus.fleetPrefix}{photo.bus.fleetNumber}<br />
              <Glyphicon glyph="subtitles" /> {photo.bus.licensePlateNumber}
            </div>
          ),
          link: `/photos/${photo.id}`,
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
            <Breadcrumb.Item
              componentClass={Link}
              to={`/categories/${category.id}`}
              href={`/categories/${category.id}`}
            >
              {category.name}
            </Breadcrumb.Item>
            <Breadcrumb.Item active>
              {advertisement.name}
            </Breadcrumb.Item>
          </Breadcrumb>
          <PhotoCardList cards={photoCards} />
        </div>
      );
    }
  }
}

PhotoListByAdvertisementView.propTypes = propTypes;
