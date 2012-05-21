package tiny.editor

class TinyEditorTagLib {

	static namespace = "tiny"

	static final DEFAULT_POSITION = 'top'
	static final DEFAULT_MODE = 'textareas'
	static final DEFAULT_THEME = 'advanced'
	static final DEFAULT_CLASS = 'editor'
//	static final DEFAULT_EDITOR_SELECTOR = 'mcesimple'
	static final DEFAULT_DIRECTION = 'ltr'
	static final DEFAULT_LANGUAGE  = 'en'
	static final DEFAULT_FONT_SIZE = "10px,12px,14px,16px,24px"

	def resources = {
		String tinyMce = g.resource(plugin: "tiny-editor", dir: "js/tiny_mce", file: "tiny_mce.js").encodeAsHTML();
		out << """<script type="text/javascript" src="${tinyMce}"></script>"""
	}

	def editor = { attrs , body ->
		String mode = attrs.remove('mode')?:DEFAULT_MODE
		String theme = attrs.remove('theme')?:DEFAULT_THEME
//		String editor_selector =  attrs.remove('selector')?:DEFAULT_EDITOR_SELECTOR
		String className =  attrs.remove('class')?:DEFAULT_CLASS
		String plugins = attrs.remove('plugins')?:''
		String theme_advanced_buttons1 = attrs.remove('theme_advanced_buttons1')?:''
		String theme_advanced_buttons2 = attrs.remove('theme_advanced_buttons2')?:''
		String theme_advanced_buttons3 = attrs.remove('theme_advanced_buttons3')?:''
		String theme_advanced_buttons1_add = attrs.remove('theme_advanced_buttons1_add')?:''
		String theme_advanced_buttons2_add = attrs.remove('theme_advanced_buttons2_add')?:''
		String theme_advanced_buttons3_add = attrs.remove('theme_advanced_buttons3_add')?:''
		String position = attrs.remove('position') ?: DEFAULT_POSITION
		String language = attrs.remove('language') ?: DEFAULT_LANGUAGE
		String directionality = attrs.remove('dir') ?: DEFAULT_DIRECTION
		String theme_advanced_font_sizes = attrs.remove('theme_advanced_font_sizes')?:DEFAULT_FONT_SIZE
		out << """<script type="text/javascript">"""
//		editor_selector : "${editor_selector}",

		out << """tinyMCE.init({
                                mode : "${mode}",
                                theme : "${theme}",
                                theme_advanced_toolbar_location : "${position}",
								language: "${language}",
								directionality: "${directionality}",
								plugins: "${plugins}",
								theme_advanced_buttons1: "${theme_advanced_buttons1}",
								theme_advanced_buttons2: "${theme_advanced_buttons2}",
								theme_advanced_buttons3: "${theme_advanced_buttons3}",
								theme_advanced_buttons1_add: "${theme_advanced_buttons1_add}",
								theme_advanced_buttons2_add: "${theme_advanced_buttons2_add}",
								theme_advanced_buttons3_add: "${theme_advanced_buttons3_add}",
								theme_advanced_font_sizes: "${theme_advanced_font_sizes}"
                            });"""
		out << """</script>"""

		// render the textarea
		out << "<textarea "
		attrs.each {key, value ->
			out << "$key='$value'"
		}
		out << "class='${className}' >"
		out << body()
		out << """</textarea>"""

	}
}