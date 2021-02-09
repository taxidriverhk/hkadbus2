import { DateTime } from 'luxon';
import PropTypes from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import {
  Glyphicon,
  Image,
  Media,
  Table
} from 'react-bootstrap';

import LinkedButton from 'shared/components/linked-button';
import styles from 'photo/style/style.scss';
import { DATE_FORMAT } from 'shared/store/constants';
import { strings } from 'shared/store/localization';

const propTypes = {
  photo: PropTypes.shape(PropTypes.object).isRequired,
};

export default function PhotoDetails({
  photo: {
    busCompany,
    busModel,
    busModelId,
    fleetNumber,
    image,
    licensePlateNumber,
    routeNumber,
    uploadedDate,
    username,
  },
}) {
  return (
    <div>
      <div>
        <Image
          className={styles['photo-details-image']}
          src={image}
          responsive
        />
      </div>
      <Table
        className={styles['photo-details-table']}
        bordered
        hover
      >
        <tbody>
          <tr>
            <td colSpan={3}>
              <Media>
                <Media.Left>
                  <Glyphicon
                    className={styles['user-icon']}
                    glyph="user"
                    bsSize="large"
                  />
                </Media.Left>
                <Media.Body>
                  {`${strings.dateUploaded}: `}
                  {DateTime.fromMillis(uploadedDate).toFormat(DATE_FORMAT)}
                  <br />
                  {`${strings.uploadedBy}: `}
                  <Link to={`/users/${username}`}>
                    {username}
                  </Link>
                </Media.Body>
              </Media>
            </td>
          </tr>
          <tr>
            <td>{strings.busModel}</td>
            <td>
              {busModel}
            </td>
            <td>
              { /* TODO: correct the URL */ }
              <LinkedButton to={`/models/${busModelId}`}>
                {strings.relatedModel}
              </LinkedButton>
            </td>
          </tr>
          <tr>
            <td>{strings.fleetNumber}</td>
            <td>{fleetNumber}</td>
            <td>
              { /* TODO: get fleet prefix */ }
              <LinkedButton to={`/fleets/not-implemented`}>
                {strings.relatedFleet}
              </LinkedButton>
            </td>
          </tr>
          <tr>
            <td>{strings.licensePlateNumber}</td>
            <td>
              <span className={styles['license-plate']}>
                {licensePlateNumber.slice(0, 2)} {licensePlateNumber.slice(2)}
              </span>
            </td>
            <td>
              { /* TODO: correct the URL */ }
              <LinkedButton to={`/buses/not-implemented`}>
                {strings.relatedBus}
              </LinkedButton>
            </td>
          </tr>
          <tr>
            <td>{strings.busCompany}</td>
            <td colSpan={2}>{busCompany}</td>
          </tr>
          <tr>
            <td>{strings.routeNumber}</td>
            <td>
              <span className={styles['route-number']}>
                {routeNumber}
              </span>
            </td>
            <td>
              { /* TODO: correct the URL */ }
              <LinkedButton to={`/routes/${routeNumber}`}>
                {strings.relatedRoute}
              </LinkedButton>
            </td>
          </tr>
        </tbody>
      </Table>
    </div>
  );
}

PhotoDetails.propTypes = propTypes;