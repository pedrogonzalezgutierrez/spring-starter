import React from 'react';
import Header from './Header.js'

import ReactDOM from 'react-dom';
import { Navbar, Nav, NavItem, Glyphicon } from 'react-bootstrap';

class App extends React.Component {
    render() {
        return (
            <div>
                <Header/>

                <Glyphicon glyph="glyphicon glyphicon-asterisk" />

                Hello React!
            </div>
        );
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);