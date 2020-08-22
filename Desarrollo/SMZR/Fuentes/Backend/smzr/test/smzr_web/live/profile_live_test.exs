defmodule SmzrWeb.ProfileLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Accounts

  @create_attrs %{birthdate: ~D[2010-04-17], dni: "some dni", email: "some email", firstname: "some firstname", lastnamem: "some lastnamem", lastnamep: "some lastnamep"}
  @update_attrs %{birthdate: ~D[2011-05-18], dni: "some updated dni", email: "some updated email", firstname: "some updated firstname", lastnamem: "some updated lastnamem", lastnamep: "some updated lastnamep"}
  @invalid_attrs %{birthdate: nil, dni: nil, email: nil, firstname: nil, lastnamem: nil, lastnamep: nil}

  defp fixture(:profile) do
    {:ok, profile} = Accounts.create_profile(@create_attrs)
    profile
  end

  defp create_profile(_) do
    profile = fixture(:profile)
    %{profile: profile}
  end

  describe "Index" do
    setup [:create_profile]

    test "lists all profiles", %{conn: conn, profile: profile} do
      {:ok, _index_live, html} = live(conn, Routes.profile_index_path(conn, :index))

      assert html =~ "Listing Profiles"
      assert html =~ profile.dni
    end

    test "saves new profile", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.profile_index_path(conn, :index))

      assert index_live |> element("a", "New Profile") |> render_click() =~
               "New Profile"

      assert_patch(index_live, Routes.profile_index_path(conn, :new))

      assert index_live
             |> form("#profile-form", profile: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#profile-form", profile: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_index_path(conn, :index))

      assert html =~ "Profile created successfully"
      assert html =~ "some dni"
    end

    test "updates profile in listing", %{conn: conn, profile: profile} do
      {:ok, index_live, _html} = live(conn, Routes.profile_index_path(conn, :index))

      assert index_live |> element("#profile-#{profile.id} a", "Edit") |> render_click() =~
               "Edit Profile"

      assert_patch(index_live, Routes.profile_index_path(conn, :edit, profile))

      assert index_live
             |> form("#profile-form", profile: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#profile-form", profile: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_index_path(conn, :index))

      assert html =~ "Profile updated successfully"
      assert html =~ "some updated dni"
    end

    test "deletes profile in listing", %{conn: conn, profile: profile} do
      {:ok, index_live, _html} = live(conn, Routes.profile_index_path(conn, :index))

      assert index_live |> element("#profile-#{profile.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#profile-#{profile.id}")
    end
  end

  describe "Show" do
    setup [:create_profile]

    test "displays profile", %{conn: conn, profile: profile} do
      {:ok, _show_live, html} = live(conn, Routes.profile_show_path(conn, :show, profile))

      assert html =~ "Show Profile"
      assert html =~ profile.dni
    end

    test "updates profile within modal", %{conn: conn, profile: profile} do
      {:ok, show_live, _html} = live(conn, Routes.profile_show_path(conn, :show, profile))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit Profile"

      assert_patch(show_live, Routes.profile_show_path(conn, :edit, profile))

      assert show_live
             |> form("#profile-form", profile: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#profile-form", profile: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.profile_show_path(conn, :show, profile))

      assert html =~ "Profile updated successfully"
      assert html =~ "some updated dni"
    end
  end
end
