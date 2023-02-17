import PropTypes from 'prop-types';

export const BusCompanyColors = {
  kmb: 'red',
  ctb: 'yellow',
  nwfb: 'green',
  cmb: 'light_blue',
};

export const photoCardType = PropTypes.shape({
  photo: PropTypes.string.isRequired,
  caption: PropTypes.string,
  description: PropTypes.node,
  extra: PropTypes.string,
  link: PropTypes.string,
  backgroundColor: PropTypes.oneOf([
    'red', 'yellow', 'green', 'light_blue', 'blue', '']),
});

export const idNameType = PropTypes.shape({
  id: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
});

export const segmentType = PropTypes.shape({
  id: PropTypes.string.isRequired,
  photo: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
  link: PropTypes.string.isRequired
});

export const segmentGroupType = {
  title: PropTypes.string,
  segments: PropTypes.arrayOf(PropTypes.shape(segmentType))
};
