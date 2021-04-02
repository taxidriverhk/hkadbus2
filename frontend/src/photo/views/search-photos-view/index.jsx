import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import { Breadcrumb } from 'react-bootstrap';

import getColorByBusCompany from 'photo/utils/bus-company-color';
import PhotoCardList from 'photo/components/photo-card-list';
import PhotoSearchFilter from 'photo/components/photo-search-filter';
import { strings } from 'shared/store/localization';
import ErrorMessageBox from 'shared/components/error-message-box';
import Loader from 'shared/components/loader';
import NoResults from 'shared/components/no-results';

const propTypes = {
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.string.isRequired,
  filter: PropTypes.shape({}).isRequired,
  onFilter: PropTypes.func.isRequired,
  results: PropTypes.arrayOf(PropTypes.shape({})).isRequired,
};

export default function SearchPhotosView({
  isFetching,
  error,
  filter,
  onFilter,
  results,
}) {
  return (
    <React.Fragment>
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
          to="/search"
          href="/search"
        >
          {strings.search}
        </Breadcrumb.Item>
      </Breadcrumb>
      <PhotoSearchFilter
        filter={filter}
        onFilter={onFilter}
      />
      {(() => {
        if (isFetching) {
          return <Loader />;
        } else if (error) {
          return <ErrorMessageBox />;
        } else if (results.length < 1) {
          return <NoResults />;
        } else {
          const searchResultCards = results.map(({
            busCompany,
            photoId,
            fleetPrefix,
            fleetNumber,
            licensePlateNumber,
            thumbnail
          }) => ({
            backgroundColor: getColorByBusCompany(busCompany),
            caption: `${fleetPrefix}${fleetNumber}`,
            description: licensePlateNumber,
            link: `/photos/${photoId}`,
            photo: thumbnail,
          }));
          return (
            <PhotoCardList
              cards={searchResultCards}
            />
          );
        }
      })()}
    </React.Fragment>
  );
}

SearchPhotosView.propTypes = propTypes;
