import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import { Breadcrumb } from 'react-bootstrap';

import { strings } from 'shared/store/localization';
import ErrorMessageBox from 'shared/components/error-message-box';
import Loader from 'shared/components/loader';
import PhotoCardList from 'photo/components/photo-card-list';

const propTypes = {
  categories: PropTypes.arrayOf(PropTypes.object).isRequired,
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.string.isRequired
};

export default function CategoriesView({
  categories,
  isFetching,
  error,
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
        <Breadcrumb.Item active>
          {strings.categories}
        </Breadcrumb.Item>
      </Breadcrumb>
      {(() => {
        if (isFetching) {
          return <Loader />;
        } else if (error) {
          return <ErrorMessageBox />;
        } else {
          const categoryCards = categories.map(category => ({
            photo: category.thumbnail,
            caption: category.name,
            link: `/categories/${category.id}`,
          }));
      
          return (
            <React.Fragment>
              
              <PhotoCardList cards={categoryCards} />
            </React.Fragment>
          );
        }
      })()}
    </React.Fragment>
  );
}

CategoriesView.propTypes = propTypes;
