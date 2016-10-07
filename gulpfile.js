var gulp = require('gulp');
var concat = require('gulp-concat');
var concatVendor = require('gulp-concat-vendor');
var uglify = require('gulp-uglify');
var minify = require('gulp-minify-css');
var mainBowerFiles = require('main-bower-files');
var inject = require('gulp-inject');
var runSequence = require('gulp-run-sequence');
var gzip = require('gulp-gzip');
var clone = require('gulp-clone');
var series = require('stream-series');
var filter = require('gulp-filter');


gulp.task('bower-js-libs', function () {
    gulp.src(mainBowerFiles(), {base: 'bower_components'})
        .pipe(filter('**/*.js'))
        .pipe(concat('lib.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('src/main/webapp/provider/js'));
});

gulp.task('bower-css', function () {
    gulp.src(mainBowerFiles(), {base: 'bower_components'})
        .pipe(filter('**/*.css'))
        .pipe(concat('lib.min.css'))
        .pipe(minify())
        .pipe(gulp.dest('src/main/webapp/provider/css'));
});

gulp.task('copy-fonts', function() {
    gulp.src(mainBowerFiles('**/dist/fonts/*.{ttf,woff,woff2,eof,svg}'))
        .pipe(gulp.dest('src/main/webapp/provider/css/bootstrap/dist/fonts/'));
});

// Default Task
gulp.task('default', function () {
    runSequence('copy-fonts');
});