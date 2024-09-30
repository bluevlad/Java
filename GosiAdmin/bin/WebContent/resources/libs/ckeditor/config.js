/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar = 'WkToolbar';
	 
	config.toolbar_WkToolbar =
		[
			{ name: 'clipboard', items : [ 'Cut','Copy','Paste' ] },
			{ name: 'basicstyles', items : [ 'Bold','Italic','Underline'] },
			
			{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock' ] },
			{ name: 'insert', items : [ 'Image','Table','HorizontalRule', 'Link'] },
			'/',
			{ name: 'styles', items : [ 'Font','FontSize' ] },
			{ name: 'colors', items : [ 'TextColor','BGColor' ] },
			{ name: 'tools', items : [ 'Maximize', 'Source', 'Preview' ] }
		];


};
