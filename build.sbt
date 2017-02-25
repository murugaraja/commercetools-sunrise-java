import sbt.Keys._
import sbt._

name := "commercetools-sunrise"

organization in ThisBuild := "com.commercetools.sunrise"

scalaVersion in ThisBuild := "2.11.8"

javacOptions in ThisBuild ++= Seq("-source", "1.8", "-target", "1.8")

resolvers in ThisBuild ++= Seq (
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.mavenLocal
)

Release.publishSettings

Heroku.deploySettings

Version.generateVersionInfo

val childProjects: List[sbt.ProjectReference] =
  List(common, `product-catalog`, `shopping-cart`, `my-account`, `test-lib`, `move-to-sdk`, `theme-importer`)

lazy val `commercetools-sunrise` = (project in file("."))
  .enablePlugins(PlayJava, JavaUnidocPlugin)
  .settings(unidocProjectFilter in (JavaUnidoc, unidoc) := inProjects(childProjects: _*))
  .settings(Release.disablePublish: _*)
  .settings(Dependencies.sunriseDefaultTheme)
  .aggregate(childProjects: _*)
  .dependsOn(`product-catalog`, `shopping-cart`, `my-account`)

lazy val common = project
  .enablePlugins(PlayJava)
  .configs(IntegrationTest, TestCommon.PlayTest)
  .settings(Release.enableSignedRelease ++ TestCommon.defaultSettings: _*)
  .settings(Dependencies.jvmSdk ++ Dependencies.sunriseTheme ++ Dependencies.sunriseModules ++ Dependencies.commonLib: _*)
  .dependsOn(`move-to-sdk`, `test-lib` % "test")

lazy val `product-catalog` = project
  .enablePlugins(PlayJava)
  .configs(IntegrationTest, TestCommon.PlayTest)
  .settings(Release.enableSignedRelease ++ TestCommon.defaultSettings: _*)
  .dependsOn(commonWithTests: _*)

lazy val `shopping-cart` = project
  .enablePlugins(PlayJava)
  .configs(IntegrationTest, TestCommon.PlayTest)
  .settings(Release.enableSignedRelease ++ TestCommon.defaultSettings: _*)
  .dependsOn(commonWithTests: _*)

lazy val `my-account` = project
  .enablePlugins(PlayJava)
  .configs(IntegrationTest, TestCommon.PlayTest)
  .settings(Release.enableSignedRelease ++ TestCommon.defaultSettings: _*)
  .dependsOn(commonWithTests: _*)

lazy val `test-lib` = project
  .enablePlugins(PlayJava)
  .settings(Release.enableSignedRelease ++ TestCommon.configCommonTestSettings("compile") ++ TestCommon.configJavaWsDependency("compile"): _*)
  .settings(Dependencies.jvmSdk ++ Dependencies.commonLib: _*)

lazy val `theme-importer` = project
  .enablePlugins(PlayJava, ThemeImporterPlugin)
  .configs(IntegrationTest)
  .settings(Release.enableSignedRelease ++ TestCommon.settingsWithoutPlayTest ++ enableLibFolderInTest: _*)

lazy val `move-to-sdk` = project
  .configs(IntegrationTest)
  .settings(Release.enableSignedRelease ++ TestCommon.settingsWithoutPlayTest: _*)
  .settings(Dependencies.jvmSdk)

lazy val commonWithTests: Seq[ClasspathDep[ProjectReference]] = Seq(common, `test-lib` % "test")

lazy val enableLibFolderInTest = unmanagedBase in Test := baseDirectory.value / "test" / "lib"