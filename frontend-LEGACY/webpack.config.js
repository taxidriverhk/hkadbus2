const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

// Configuration separated by development/production mode
const htmlPluginConfigs = {
  development: {
    template: './templates/index.html'
  },
  production: {
    template: './templates/index.html',
    filename: '../index.html'
  }
};
const outputConfigs = {
  development: {
    publicPath: '/',
    path: path.resolve(__dirname, 'dist'),
    filename: 'bundle.js'
  },
  production: {
    path: path.resolve(__dirname, 'build/scripts'),
    filename: '[name].[contenthash].min.js',
    chunkFilename: '[name].[contenthash].min.js'
  }
};

module.exports = (_, argv) => {
  const mode = argv.mode;
  return {
    entry: ['babel-polyfill', './index.jsx'],
    output: outputConfigs[mode],

    devtool: 'inline-source-map',
    devServer: {
      publicPath: '/',
      disableHostCheck: true,
      historyApiFallback: true,
      port: 8888,
      proxy: {
        '/config/fetch-config.json': {
          target: 'http://localhost:8888',
          pathRewrite: {
            '/config/fetch-config.json': '/config/fetch-dev-config.json'
          },
          changeOrigin: true,
          secure: false,
        }
      }
    },

    module: {
      rules: [
        {
          test: /\.(js|jsx)$/,
          loader: 'babel-loader',
          exclude: /node_modules/
        },
        {
          test: /\.(css|scss)$/,
          use: [
            'style-loader',
            {
              loader: 'css-loader',
              options: {
                modules: true
              }
            },
            'sass-loader'
          ]
        },
        {
          test: /\.(png|jpg|ttf|eot|svg)$/,
          loader: 'file-loader'
        },
        {
          test: /\.(html)$/,
          loader: 'html-loader'
        },
        {
          test: /\.(woff|woff2)$/,
          use: {
            loader: 'url-loader',
            options: {
              name: 'fonts/[hash].[ext]',
              limit: 5000,
              mimetype: 'application/font-woff'
            }
          }
        }
      ]
    },

    optimization: {
      minimizer: [
        new UglifyJsPlugin()
      ],
      runtimeChunk: true,
      splitChunks: {
        chunks: 'all',
        cacheGroups: {
          vendors: {
            test: /node_modules/,
            name: 'vendors',
            enforce: true
          }
        }
      }
    },
    plugins: [
      new HtmlWebpackPlugin(htmlPluginConfigs[mode])
    ],
    resolve: {
      extensions: ['.js', '.jsx'],
      modules: [
        path.resolve('./src'),
        path.resolve('./node_modules')
      ]
    }
  };
};