<?xml version="1.0" encoding="UTF-8"?>
<!--
$URL: svn+ssh://concyclic@svn.code.sf.net/p/mathjax4svg/code/sources/site/xhtml.xsl $
$Id: xhtml.xsl 5 2014-06-05 01:35:35Z concyclic $
-->
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:d="http://docbook.org/ns/docbook"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns:xslthl="http://xslthl.sf.net"
	xmlns:lxslt="http://xml.apache.org/xslt"
	exclude-result-prefixes="xlink lxslt d xslthl #default"
	version="1.1"
	>
	<xsl:import href="http://docbook.sourceforge.net/release/xsl-ns/current/xhtml-1_1/profile-chunk.xsl"/>
	<xsl:import href="highlight.xsl"/>
	<xsl:import href="http://docbook.sourceforge.net/release/xsl-ns/current/xhtml-1_1/autoidx-kosek.xsl"/>

	<xsl:param name="html.extra.head.links" select="1"/>
	<xsl:param name="header.rule" select="0"/>
	<xsl:param name="footer.rule" select="0"/>
	<xsl:param name="blurb.on.titlepage.enabled" select="1"/>
	<xsl:param name="callout.graphics" select="0"/>
	<xsl:param name="callout.unicode" select="1"/>
	<xsl:param name="admon.graphics" select="1"/>
	<xsl:param name="admon.graphics.path">admonitions/</xsl:param>
	<xsl:param name="admon.graphics.extension">.svg</xsl:param>
	<xsl:param name="admon.style"/>
	<xsl:param name="textinsert.extension" select="1"/>
	<xsl:param name="use.id.as.filename" select="1"/>
	<xsl:param name="index.method">kosek</xsl:param>
	<xsl:param name="use.svg" select="1"/>
	<xsl:param name="variablelist.term.break.after" select="1"/>
	<xsl:param name="index.on.type" select="1"/>
	<xsl:param name="use.extensions" select="1"/>
	<xsl:param name="make.graphic.viewport" select="1"/>
	<xsl:param name="highlight.source" select="1"/>
	<xsl:param name="variablelist.term.separator"/>
	<xsl:param name="css.decoration">1</xsl:param>
	<xsl:param name="graphicsize.extension" select="0"/>
	<xsl:param name="graphicsize.use.img.src.path" select="1"/>
	<xsl:param name="make.year.ranges" select="1"/>
	<xsl:param name="navig.graphics" select="1"/>
	<xsl:param name="navig.graphics.path">navigation/</xsl:param>
	<xsl:param name="navig.graphics.extension">.svg</xsl:param>
	<xsl:param name="shortcut.icon"/>
	<xsl:param name="keep.relative.image.uris" select="1"/>
	<xsl:param name="make.clean.html" select="1"/>
	<xsl:param name="docbook.css.source"/>
	<xsl:param name="table.borders.with.css" select="0"/>
	<xsl:param name="callout.unicode.font" select="'STIX General'"/>
	<xsl:param name="section.autolabel" select="1"/>
	<xsl:param name="arg.rep.repeat.str">…</xsl:param>
	<xsl:param name="section.label.includes.component.label" select="1"/>
	<xsl:param name="local.l10n.xml" select="document('l10n.xml')"/>

	<xsl:output method="xml" omit-xml-declaration="yes"/>

	<xsl:template name="root.attributes">
		<xsl:attribute name="xml:lang">
			<xsl:value-of select="ancestor-or-self::d:*/@xml:lang"/>
		</xsl:attribute>
	</xsl:template>

	<xsl:template name="user.head.content">
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<link rel="shortcut icon" href="{$shortcut.icon}" type="image/png"/>
		<link rel="author" href="https://plus.google.com/113215332542811715537?rel=author"/>
		<script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=MML_HTMLorMML" type="application/ecmascript"><xsl:comment/></script>
		<meta name="google-site-verification" content="93Z31Ol33qRY3Xte6j53s1OTLxxuTm7kAmVXUAhUHLA"/>
		<script>(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new
			Date();a=s.createElement(o),
			m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
			ga('create', 'UA-23282328-2', 'sourceforge.net');
			ga('send', 'pageview');</script>
	</xsl:template>

	<xsl:template name="user.footer.navigation">
		<!--a href="http://sourceforge.net"><img
			src="http://sflogo.sourceforge.net/sflogo.php?group_id=2246943&amp;type=4"
			alt="SourceForge.net Logo"/></a-->
	</xsl:template>

	<xsl:template name="is.graphic.format">
		<xsl:param name="format"/>
		<xsl:if test="$format='SVG'
				or $format='PNG'
				or $format='JPG'
				or $format='JPEG'
				or $format='linespecific'
				or $format='GIF'
				or $format='GIF87a'
				or $format='GIF89a'
				or $format='BMP'
				or $format='HTML'
				or $format='XHTML'">1</xsl:if>
	</xsl:template>

	<xsl:template match="d:imagedata[@format='XHTML']">
		<object
			type="application/xhtml+xml"
			data="{@fileref}"
			width="{@width}"
			height="{@height}"
			/>
	</xsl:template>

	<xsl:template match="d:token|d:code">
		<code class="{name(.)}">
			<xsl:choose>
				<xsl:when test="$highlight.source!=0">
					<xsl:call-template name="apply-highlighting"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates/>
				</xsl:otherwise>
			</xsl:choose>
		</code>
	</xsl:template>

</xsl:stylesheet>
