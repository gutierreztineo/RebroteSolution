defmodule SmzrWeb.UserLocationLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Tracking

  @create_attrs %{}
  @update_attrs %{}
  @invalid_attrs %{}

  defp fixture(:user_location) do
    {:ok, user_location} = Tracking.create_user_location(@create_attrs)
    user_location
  end

  defp create_user_location(_) do
    user_location = fixture(:user_location)
    %{user_location: user_location}
  end

  describe "Index" do
    setup [:create_user_location]

    test "lists all user_locations", %{conn: conn, user_location: user_location} do
      {:ok, _index_live, html} = live(conn, Routes.user_location_index_path(conn, :index))

      assert html =~ "Listing User locations"
    end

    test "saves new user_location", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.user_location_index_path(conn, :index))

      assert index_live |> element("a", "New User location") |> render_click() =~
               "New User location"

      assert_patch(index_live, Routes.user_location_index_path(conn, :new))

      assert index_live
             |> form("#user_location-form", user_location: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#user_location-form", user_location: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.user_location_index_path(conn, :index))

      assert html =~ "User location created successfully"
    end

    test "updates user_location in listing", %{conn: conn, user_location: user_location} do
      {:ok, index_live, _html} = live(conn, Routes.user_location_index_path(conn, :index))

      assert index_live |> element("#user_location-#{user_location.id} a", "Edit") |> render_click() =~
               "Edit User location"

      assert_patch(index_live, Routes.user_location_index_path(conn, :edit, user_location))

      assert index_live
             |> form("#user_location-form", user_location: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#user_location-form", user_location: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.user_location_index_path(conn, :index))

      assert html =~ "User location updated successfully"
    end

    test "deletes user_location in listing", %{conn: conn, user_location: user_location} do
      {:ok, index_live, _html} = live(conn, Routes.user_location_index_path(conn, :index))

      assert index_live |> element("#user_location-#{user_location.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#user_location-#{user_location.id}")
    end
  end

  describe "Show" do
    setup [:create_user_location]

    test "displays user_location", %{conn: conn, user_location: user_location} do
      {:ok, _show_live, html} = live(conn, Routes.user_location_show_path(conn, :show, user_location))

      assert html =~ "Show User location"
    end

    test "updates user_location within modal", %{conn: conn, user_location: user_location} do
      {:ok, show_live, _html} = live(conn, Routes.user_location_show_path(conn, :show, user_location))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit User location"

      assert_patch(show_live, Routes.user_location_show_path(conn, :edit, user_location))

      assert show_live
             |> form("#user_location-form", user_location: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#user_location-form", user_location: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.user_location_show_path(conn, :show, user_location))

      assert html =~ "User location updated successfully"
    end
  end
end
