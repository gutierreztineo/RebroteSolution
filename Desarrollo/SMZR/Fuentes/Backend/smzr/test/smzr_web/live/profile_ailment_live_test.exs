defmodule SmzrWeb.ProfileAilmentLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Monitoring

  @create_attrs %{}
  @update_attrs %{}
  @invalid_attrs %{}

  defp fixture(:profile_ailment) do
    {:ok, profile_ailment} = Monitoring.create_profile_ailment(@create_attrs)
    profile_ailment
  end

  defp create_profile_ailment(_) do
    profile_ailment = fixture(:profile_ailment)
    %{profile_ailment: profile_ailment}
  end

  describe "Index" do
    setup [:create_profile_ailment]

    test "lists all profile_ailments", %{conn: conn, profile_ailment: profile_ailment} do
      {:ok, _index_live, html} = live(conn, Routes.profile_ailment_index_path(conn, :index))

      assert html =~ "Listing Profile ailments"
    end

    test "saves new profile_ailment", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.profile_ailment_index_path(conn, :index))

      assert index_live |> element("a", "New Profile ailment") |> render_click() =~
               "New Profile ailment"

      assert_patch(index_live, Routes.profile_ailment_index_path(conn, :new))

      assert index_live
             |> form("#profile_ailment-form", profile_ailment: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#profile_ailment-form", profile_ailment: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_ailment_index_path(conn, :index))

      assert html =~ "Profile ailment created successfully"
    end

    test "updates profile_ailment in listing", %{conn: conn, profile_ailment: profile_ailment} do
      {:ok, index_live, _html} = live(conn, Routes.profile_ailment_index_path(conn, :index))

      assert index_live |> element("#profile_ailment-#{profile_ailment.id} a", "Edit") |> render_click() =~
               "Edit Profile ailment"

      assert_patch(index_live, Routes.profile_ailment_index_path(conn, :edit, profile_ailment))

      assert index_live
             |> form("#profile_ailment-form", profile_ailment: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#profile_ailment-form", profile_ailment: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_ailment_index_path(conn, :index))

      assert html =~ "Profile ailment updated successfully"
    end

    test "deletes profile_ailment in listing", %{conn: conn, profile_ailment: profile_ailment} do
      {:ok, index_live, _html} = live(conn, Routes.profile_ailment_index_path(conn, :index))

      assert index_live |> element("#profile_ailment-#{profile_ailment.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#profile_ailment-#{profile_ailment.id}")
    end
  end

  describe "Show" do
    setup [:create_profile_ailment]

    test "displays profile_ailment", %{conn: conn, profile_ailment: profile_ailment} do
      {:ok, _show_live, html} = live(conn, Routes.profile_ailment_show_path(conn, :show, profile_ailment))

      assert html =~ "Show Profile ailment"
    end

    test "updates profile_ailment within modal", %{conn: conn, profile_ailment: profile_ailment} do
      {:ok, show_live, _html} = live(conn, Routes.profile_ailment_show_path(conn, :show, profile_ailment))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit Profile ailment"

      assert_patch(show_live, Routes.profile_ailment_show_path(conn, :edit, profile_ailment))

      assert show_live
             |> form("#profile_ailment-form", profile_ailment: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#profile_ailment-form", profile_ailment: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_ailment_show_path(conn, :show, profile_ailment))

      assert html =~ "Profile ailment updated successfully"
    end
  end
end
