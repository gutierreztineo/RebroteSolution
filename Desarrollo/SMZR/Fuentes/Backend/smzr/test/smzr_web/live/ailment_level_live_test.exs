defmodule SmzrWeb.AilmentLevelLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Monitoring

  @create_attrs %{description: "some description", level: 42}
  @update_attrs %{description: "some updated description", level: 43}
  @invalid_attrs %{description: nil, level: nil}

  defp fixture(:ailment_level) do
    {:ok, ailment_level} = Monitoring.create_ailment_level(@create_attrs)
    ailment_level
  end

  defp create_ailment_level(_) do
    ailment_level = fixture(:ailment_level)
    %{ailment_level: ailment_level}
  end

  describe "Index" do
    setup [:create_ailment_level]

    test "lists all ailment_levels", %{conn: conn, ailment_level: ailment_level} do
      {:ok, _index_live, html} = live(conn, Routes.ailment_level_index_path(conn, :index))

      assert html =~ "Listing Ailment levels"
      assert html =~ ailment_level.description
    end

    test "saves new ailment_level", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_level_index_path(conn, :index))

      assert index_live |> element("a", "New Ailment level") |> render_click() =~
               "New Ailment level"

      assert_patch(index_live, Routes.ailment_level_index_path(conn, :new))

      assert index_live
             |> form("#ailment_level-form", ailment_level: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#ailment_level-form", ailment_level: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_level_index_path(conn, :index))

      assert html =~ "Ailment level created successfully"
      assert html =~ "some description"
    end

    test "updates ailment_level in listing", %{conn: conn, ailment_level: ailment_level} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_level_index_path(conn, :index))

      assert index_live |> element("#ailment_level-#{ailment_level.id} a", "Edit") |> render_click() =~
               "Edit Ailment level"

      assert_patch(index_live, Routes.ailment_level_index_path(conn, :edit, ailment_level))

      assert index_live
             |> form("#ailment_level-form", ailment_level: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#ailment_level-form", ailment_level: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_level_index_path(conn, :index))

      assert html =~ "Ailment level updated successfully"
      assert html =~ "some updated description"
    end

    test "deletes ailment_level in listing", %{conn: conn, ailment_level: ailment_level} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_level_index_path(conn, :index))

      assert index_live |> element("#ailment_level-#{ailment_level.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#ailment_level-#{ailment_level.id}")
    end
  end

  describe "Show" do
    setup [:create_ailment_level]

    test "displays ailment_level", %{conn: conn, ailment_level: ailment_level} do
      {:ok, _show_live, html} = live(conn, Routes.ailment_level_show_path(conn, :show, ailment_level))

      assert html =~ "Show Ailment level"
      assert html =~ ailment_level.description
    end

    test "updates ailment_level within modal", %{conn: conn, ailment_level: ailment_level} do
      {:ok, show_live, _html} = live(conn, Routes.ailment_level_show_path(conn, :show, ailment_level))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit Ailment level"

      assert_patch(show_live, Routes.ailment_level_show_path(conn, :edit, ailment_level))

      assert show_live
             |> form("#ailment_level-form", ailment_level: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#ailment_level-form", ailment_level: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_level_show_path(conn, :show, ailment_level))

      assert html =~ "Ailment level updated successfully"
      assert html =~ "some updated description"
    end
  end
end
