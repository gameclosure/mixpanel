<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:android="http://schemas.android.com/apk/res/android">

	<xsl:param name="mixpanelToken" />

	<xsl:output indent="yes" />
	<xsl:template match="comment()" />

	<xsl:template match="meta-data[@android:name='MIXPANEL_TOKEN']">
		<meta-data android:name="MIXPANEL_TOKEN" android:value="{$mixpanelToken}"/>
	</xsl:template>

	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>
