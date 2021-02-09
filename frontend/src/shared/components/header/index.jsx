import { func, string } from 'prop-types';
import React from 'react';
import { Link } from 'react-router-dom';

import {
  Glyphicon,
  Nav,
  Navbar,
  NavItem,
} from 'react-bootstrap';

import { strings } from 'shared/store/localization';
import styles from './style/header.scss';

const propTypes = {
  headerImg: string.isRequired,
  language: string.isRequired,
  onChangeLanguage: func.isRequired,
};

export default function Header(props) {
  const {
    headerImg,
    language,
    onChangeLanguage,
  } = props;

  return (
    <React.Fragment>
      <div className={styles['header-image']} style={{
        backgroundImage: `url(${headerImg})`
      }} />
      <Navbar inverse fluid>
        <Navbar.Header>
          <Navbar.Brand className={styles['navbar-title']}>
            <Link to="/">{strings.title}</Link>
          </Navbar.Brand>
        </Navbar.Header>
        <Navbar.Collapse>
          <Nav>
            <NavItem 
              eventKey={1}
              componentClass={Link}
              role="button"
              href="/categories"
              to="/categories"
            >
              <Glyphicon glyph="th-list" /> {strings.categories}
            </NavItem>
            <NavItem
              eventKey={2}
              componentClass={Link}
              role="button"
              href="/models"
              to="/models"
            >
              <Glyphicon glyph="modal-window" /> {strings.models}
            </NavItem>
          </Nav>
          <Nav pullRight activeKey={language === 'en_us' ? 1 : 2}>
            <NavItem eventKey={1} onClick={onChangeLanguage('en_us')}>
                            Eng
            </NavItem>
            <NavItem eventKey={2} onClick={onChangeLanguage('zh_hk')}>
                            中
            </NavItem>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </React.Fragment>
  );
}

Header.propTypes = propTypes;
