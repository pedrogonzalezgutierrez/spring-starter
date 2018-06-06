var path = require('path');

module.exports = {
    entry: './src/main/js/app.js',
    output: {
        path: './src/main/resources/static/built/',
        filename: 'bundle.js'
    },

    module: {
        loaders: [
            {
                exclude: /(node_modules|bower_components)/,
                loader: 'babel',
                query: {
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
};