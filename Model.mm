<map version="freeplane 1.3.0">
<!--To view this file, download free mind mapping software Freeplane from http://freeplane.sourceforge.net -->
<node TEXT="Root" ID="ID_1723255651" CREATED="1283093380553" MODIFIED="1450263422455" STYLE="bubble"><hook NAME="MapStyle" zoom="1.771">
    <properties show_icon_for_attributes="true" show_note_icons="true"/>

<map_styles>
<stylenode LOCALIZED_TEXT="styles.root_node">
<stylenode LOCALIZED_TEXT="styles.predefined" POSITION="right">
<stylenode LOCALIZED_TEXT="default" MAX_WIDTH="600" COLOR="#000000" STYLE="as_parent">
<font NAME="SansSerif" SIZE="10" BOLD="false" ITALIC="false"/>
</stylenode>
<stylenode LOCALIZED_TEXT="defaultstyle.details"/>
<stylenode LOCALIZED_TEXT="defaultstyle.note"/>
<stylenode LOCALIZED_TEXT="defaultstyle.floating">
<edge STYLE="hide_edge"/>
<cloud COLOR="#f0f0f0" SHAPE="ROUND_RECT"/>
</stylenode>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.user-defined" POSITION="right">
<stylenode LOCALIZED_TEXT="styles.topic" COLOR="#18898b" STYLE="fork">
<font NAME="Liberation Sans" SIZE="10" BOLD="true"/>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.subtopic" COLOR="#cc3300" STYLE="fork">
<font NAME="Liberation Sans" SIZE="10" BOLD="true"/>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.subsubtopic" COLOR="#669900">
<font NAME="Liberation Sans" SIZE="10" BOLD="true"/>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.important">
<icon BUILTIN="yes"/>
</stylenode>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.AutomaticLayout" POSITION="right">
<stylenode LOCALIZED_TEXT="AutomaticLayout.level.root" COLOR="#000000">
<font SIZE="18"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,1" COLOR="#0033ff">
<font SIZE="16"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,2" COLOR="#00b439">
<font SIZE="14"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,3" COLOR="#990000">
<font SIZE="12"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,4" COLOR="#111111">
<font SIZE="10"/>
</stylenode>
</stylenode>
</stylenode>
</map_styles>
</hook>
<hook NAME="AutomaticEdgeColor" COUNTER="8"/>
<font SIZE="14" BOLD="true"/>
<edge COLOR="#999999" WIDTH="2"/>
<node TEXT="has Models&#xa;(can have)" POSITION="right" ID="ID_364538611" CREATED="1450262411576" MODIFIED="1450263360071" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
<font BOLD="true"/>
<node TEXT="has name and description&#xa;as mandatory fields." ID="ID_1642112096" CREATED="1450261269787" MODIFIED="1450263360072" STYLE="bubble" MIN_WIDTH="1">
<edge COLOR="#999999" WIDTH="2"/>
</node>
<node TEXT="has subjects&#xa;(can have)" ID="ID_603493002" CREATED="1450261407627" MODIFIED="1450263360073" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
<font BOLD="true"/>
<node TEXT="has name and description&#xa;as mandatory fields." ID="ID_296065081" CREATED="1450261926372" MODIFIED="1450263360074" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
<node TEXT="a name is unique in context&#xa;of the model it refers to." ID="ID_1167217452" CREATED="1450261941968" MODIFIED="1450263360075" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
<node TEXT="has connections&#xa;(can have)" ID="ID_1231754842" CREATED="1450263788521" MODIFIED="1450263805777">
<font BOLD="true"/>
<node TEXT="one connection can&#xa;be a fact" ID="ID_787861643" CREATED="1450263806173" MODIFIED="1450263850170">
<node TEXT="a fact has name and&#xa;description as mandatory fields." ID="ID_844367085" CREATED="1450263851489" MODIFIED="1450263880975"/>
<node TEXT="a name is unique in context of the&#xa;subject it refers to if it&apos;s &quot;local&quot; otherwise&#xa;it&apos;s unique in context of the model." ID="ID_422839100" CREATED="1450263881769" MODIFIED="1450263947702"/>
</node>
</node>
</node>
<node TEXT="has connections&#xa;(can have)" ID="ID_800331260" CREATED="1450262171796" MODIFIED="1450263711188" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
<font BOLD="true"/>
<node TEXT="A connection is a reference to other models" ID="ID_1487309703" CREATED="1450262201488" MODIFIED="1450263719122" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
<node TEXT="A connection stores the name of the referring model" ID="ID_1499651966" CREATED="1450262271632" MODIFIED="1450263727038" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
<node TEXT="Unresolved: serialization!" ID="ID_1971161809" CREATED="1450263733265" MODIFIED="1450263740301" BACKGROUND_COLOR="#ffcc99"/>
</node>
</node>
<node TEXT="Does know the root." ID="ID_816978584" CREATED="1450262580572" MODIFIED="1450263360077" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
<node TEXT="Unresolved: serialization!" ID="ID_945540484" CREATED="1450263043101" MODIFIED="1450263445149" BACKGROUND_COLOR="#ffcc99" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
</node>
</node>
<node TEXT="There&apos;s just one root object (intentional)" POSITION="right" ID="ID_562412647" CREATED="1450262708833" MODIFIED="1450263360077" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
<node TEXT="Serializable" POSITION="right" ID="ID_1114542598" CREATED="1450261698360" MODIFIED="1450263360078" VSHIFT="-2" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
<font BOLD="true"/>
<node TEXT="to/from JSON." ID="ID_155004035" CREATED="1450261705271" MODIFIED="1450263360078" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
<node TEXT="to/from XML." ID="ID_1218741711" CREATED="1450261710179" MODIFIED="1450263360078" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
<node TEXT="to/from XML." ID="ID_1144236481" CREATED="1450261710179" MODIFIED="1450263360078" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
</node>
<node TEXT="to/from Script (internal)." ID="ID_818358221" CREATED="1450262009001" MODIFIED="1450263360078" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
<node TEXT="Serializing of root, models, links and subjects." ID="ID_46377877" CREATED="1450262620032" MODIFIED="1450263360078" STYLE="bubble">
<edge COLOR="#999999" WIDTH="2"/>
</node>
</node>
</node>
</map>
