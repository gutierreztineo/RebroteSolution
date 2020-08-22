defmodule SmzrWeb.ProfileRiskLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Monitoring

  @create_attrs %{}
  @update_attrs %{}
  @invalid_attrs %{}

  defp fixture(:profile_risk) do
    {:ok, profile_risk} = Monitoring.create_profile_risk(@create_attrs)
    profile_risk
  end

  defp create_profile_risk(_) do
    profile_risk = fixture(:profile_risk)
    %{profile_risk: profile_risk}
  end

  describe "Index" do
    setup [:create_profile_risk]

    test "lists all profile_risks", %{conn: conn, profile_risk: profile_risk} do
      {:ok, _index_live, html} = live(conn, Routes.profile_risk_index_path(conn, :index))

      assert html =~ "Listing Profile risks"
    end

    test "saves new profile_risk", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.profile_risk_index_path(conn, :index))

      assert index_live |> element("a", "New Profile risk") |> render_click() =~
               "New Profile risk"

      assert_patch(index_live, Routes.profile_risk_index_path(conn, :new))

      assert index_live
             |> form("#profile_risk-form", profile_risk: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#profile_risk-form", profile_risk: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_risk_index_path(conn, :index))

      assert html =~ "Profile risk created successfully"
    end

    test "updates profile_risk in listing", %{conn: conn, profile_risk: profile_risk} do
      {:ok, index_live, _html} = live(conn, Routes.profile_risk_index_path(conn, :index))

      assert index_live |> element("#profile_risk-#{profile_risk.id} a", "Edit") |> render_click() =~
               "Edit Profile risk"

      assert_patch(index_live, Routes.profile_risk_index_path(conn, :edit, profile_risk))

      assert index_live
             |> form("#profile_risk-form", profile_risk: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#profile_risk-form", profile_risk: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_risk_index_path(conn, :index))

      assert html =~ "Profile risk updated successfully"
    end

    test "deletes profile_risk in listing", %{conn: conn, profile_risk: profile_risk} do
      {:ok, index_live, _html} = live(conn, Routes.profile_risk_index_path(conn, :index))

      assert index_live |> element("#profile_risk-#{profile_risk.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#profile_risk-#{profile_risk.id}")
    end
  end

  describe "Show" do
    setup [:create_profile_risk]

    test "displays profile_risk", %{conn: conn, profile_risk: profile_risk} do
      {:ok, _show_live, html} = live(conn, Routes.profile_risk_show_path(conn, :show, profile_risk))

      assert html =~ "Show Profile risk"
    end

    test "updates profile_risk within modal", %{conn: conn, profile_risk: profile_risk} do
      {:ok, show_live, _html} = live(conn, Routes.profile_risk_show_path(conn, :show, profile_risk))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit Profile risk"

      assert_patch(show_live, Routes.profile_risk_show_path(conn, :edit, profile_risk))

      assert show_live
             |> form("#profile_risk-form", profile_risk: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#profile_risk-form", profile_risk: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_risk_show_path(conn, :show, profile_risk))

      assert html =~ "Profile risk updated successfully"
    end
  end
end
