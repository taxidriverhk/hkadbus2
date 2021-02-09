import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import React, { Component } from 'react';

import fetchBuses from 'photo/actions/buses';
import BusesView from 'photo/views/buses-view';

const propTypes = {
  buses: PropTypes.arrayOf(PropTypes.shape({})).isRequired,
  busModel: PropTypes.shape({}).isRequired,
  error: PropTypes.string,
  fetchBuses: PropTypes.func.isRequired,
  isFetching: PropTypes.bool.isRequired
};

class BusesContainer extends Component {
  componentDidMount() {
    this.props.fetchBuses();
  }

  render() {
    const {
      buses,
      busModel,
      error,
      isFetching
    } = this.props;

    return (
      <BusesView
        buses={buses}
        busModel={busModel}
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
  } = state.buses;

  const busModel = {};
  const buses = {};
  return {
    buses,
    busModel,
    isFetching,
    error,
  };
};

const mapDispatchToProps = (dispatch, ownProps) => {
  const { id } = ownProps.match.params;
  const { language } = ownProps;
  return {
    fetchBuses: () => dispatch(fetchBuses(id, language))
  };
};

BusesContainer.propTypes = propTypes;

export default connect(
  mapStateToProps, 
  mapDispatchToProps
)(BusesContainer);
