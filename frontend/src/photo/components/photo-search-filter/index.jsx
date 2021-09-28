import PropTypes from 'prop-types';
import React, { useEffect, useState } from 'react';

import {
  Button,
  ControlLabel,
  InputGroup,
  FormControl,
  FormGroup,
} from 'react-bootstrap';

import { strings } from 'shared/store/localization';
import styles from './style/style.scss';

const defaultProps = {
  onFilter: Function.prototype,
  onSort: Function.prototype,
};
const propTypes = {
  filter: PropTypes.shape({}).isRequired,
  sort: PropTypes.shape({}).isRequired,
  onFilter: PropTypes.func,
  onSort: PropTypes.func,
};

export default function PhotoSearchFilter({
  filter,
  sort,
  onFilter,
  onSort,
}) {
  const [
    inputFilter,
    setInputFilter,
  ] = useState({});

  useEffect(() => {
    setInputFilter(filter);
  }, [filter]);

  const {
    q: query,
    fleetPrefix,
    fleetNumber,
    licensePlateNumber,
    routeNumber,
    username,
  } = inputFilter;

  const handleSubmit = (event) => {
    // TODO: add validation
    onFilter(inputFilter);
    event.preventDefault();
  }

  const handleInputFilterChange = (attribute) => (event) => {
    setInputFilter({
      ...inputFilter,
      [attribute]: event.currentTarget.value,
    });
  };

  return (
    <form
      className={styles['photo-search-filter-container']}
      onSubmit={handleSubmit}
    >
      <FormGroup>
        <ControlLabel>{strings.general}</ControlLabel>
        <FormControl
          onChange={handleInputFilterChange('q')}
          type="text"
          value={query}
        />

        <ControlLabel>{strings.routeNumber}</ControlLabel>
        <FormControl
          onChange={handleInputFilterChange('routeNumber')}
          type="text"
          value={routeNumber}
        />

        <ControlLabel>{strings.fleetNumber}</ControlLabel>
        <InputGroup>
          <InputGroup.Addon>{strings.fleetPrefix}</InputGroup.Addon>
          <FormControl
            onChange={handleInputFilterChange('fleetPrefix')}
            type="text"
            value={fleetPrefix}
          />
        </InputGroup>
        <InputGroup>
          <InputGroup.Addon>{strings.fleetSuffix}</InputGroup.Addon>
          <FormControl
            onChange={handleInputFilterChange('fleetNumber')}
            type="text"
            value={fleetNumber}
          />
        </InputGroup>

        <ControlLabel>{strings.licensePlateNumber}</ControlLabel>
        <FormControl
          onChange={handleInputFilterChange('licensePlateNumber')}
          type="text"
          value={licensePlateNumber}
        />

        <ControlLabel>{strings.uploadedBy}</ControlLabel>
        <FormControl
          onChange={handleInputFilterChange('username')}
          type="text"
          value={username}
        />
      </FormGroup>
      <Button
        variant="primary"
        type="submit"
      >
        {strings.search}
      </Button>
    </form>
  );
}

PhotoSearchFilter.defaultProps = defaultProps;
PhotoSearchFilter.propTypes = propTypes;
