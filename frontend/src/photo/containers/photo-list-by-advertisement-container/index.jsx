import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import React, { Component } from 'react';

import { getPhotosByAdvertisement } from 'photo/selectors/photo-list-by-advertisement';
import fetchPhotosByAdvertisementId from 'photo/actions/photo-list-by-advertisement';
import PhotoListByAdvertisementView from 'photo/views/photo-list-by-advertisement-view';
import { idNameType } from 'photo/types';

const propTypes = {
  advertisement: idNameType.isRequired,
  category: idNameType.isRequired,
  photos: PropTypes.arrayOf(PropTypes.shape({})).isRequired,
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.string,
  fetchPhotosByAdvertisementId: PropTypes.func.isRequired,
};

class PhotoListByAdvertisementContainer extends Component {
  componentDidMount() {
    this.props.fetchPhotosByAdvertisementId();
  }

  render() {
    const {
      advertisement,
      category,
      photos,
      isFetching,
      error
    } = this.props;

    return (
      <PhotoListByAdvertisementView
        advertisement={advertisement}
        category={category}
        photos={photos}
        isFetching={isFetching}
        error={error}
      />
    );
  }
}

const mapStateToProps = (state) => {
  const {
    isFetching,
    error
  } = state.photoListByAdvertisementReducer;

  const {
    photos,
    advertisement,
    category,
  } = getPhotosByAdvertisement(state);
  return {
    isFetching,
    error,
    advertisement,
    category,
    photos,
  };
};

const mapDispatchToProps = (dispatch, ownProps) => {
  const { id } = ownProps.match.params;
  const { language } = ownProps;
  return {
    fetchPhotosByAdvertisementId: () => dispatch(fetchPhotosByAdvertisementId(id, language))
  };
};

PhotoListByAdvertisementContainer.propTypes = propTypes;

export default connect(
  mapStateToProps, 
  mapDispatchToProps
)(PhotoListByAdvertisementContainer);
