import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import { Button } from 'react-bootstrap';

const defaultProps = {
  children: null
};
const propTypes = {
  children: PropTypes.node,
  to: PropTypes.string.isRequired
};

export default function LinkedButton({
  children,
  to
}) {
  return (
    <div>
      <Button
        componentClass={Link}
        bsSize="small"
        bsStyle="info"
        href={to}
        to={to}
      >
        {children}
      </Button>
    </div>
  );
}

LinkedButton.defaultProps = defaultProps;
LinkedButton.propTypes = propTypes;
