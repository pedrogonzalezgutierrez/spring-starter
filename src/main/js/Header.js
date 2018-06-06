import React from 'react';
import {Grid, Row, Col} from 'react-bootstrap';

class Header extends React.Component {
    render() {
        return (
            <div>
                <Grid>
                    <Row className="show-grid">
                        <Col xs={12}>
                            <h1 th:text="#{application.name}"></h1>
                            <h2>Example page header <small>Subtext for header</small></h2>
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}

export default Header;

