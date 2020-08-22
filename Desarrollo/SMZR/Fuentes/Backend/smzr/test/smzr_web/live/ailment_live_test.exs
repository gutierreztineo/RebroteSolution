defmodule SmzrWeb.AilmentLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Monitoring

  @create_attrs %{description: "some description"}
  @update_attrs %{description: "some updated description"}
  @invalid_attrs %{description: nil}

  defp fixture(:ailment) do
    {:ok, ailment} = Monitoring.create_ailment(@create_attrs)
    ailment
  end

  defp create_ailment(_) do
    ailment = fixture(:ailment)
    %{ailment: ailment}
  end

  describe "Index" do
    setup [:create_ailment]

    test "lists all ailments", %{conn: conn, ailment: ailment} do
      {:ok, _index_live, html} = live(conn, Routes.ailment_index_path(conn, :index))

      assert html =~ "Listing Ailments"
      assert html =~ ailment.description
    end

    test "saves new ailment", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_index_path(conn, :index))

      assert index_live |> element("a", "New Ailment") |> render_click() =~
               "New Ailment"

      assert_patch(index_live, Routes.ailment_index_path(conn, :new))

      assert index_live
             |> form("#ailment-form", ailment: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#ailment-form", ailment: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_index_path(conn, :index))

      assert html =~ "Ailment created successfully"
      assert html =~ "some description"
    end

    test "updates ailment in listing", %{conn: conn, ailment: ailment} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_index_path(conn, :index))

      assert index_live |> element("#ailment-#{ailment.id} a", "Edit") |> render_click() =~
               "Edit Ailment"

      assert_patch(index_live, Routes.ailment_index_path(conn, :edit, ailment))

      assert index_live
             |> form("#ailment-form", ailment: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#ailment-form", ailment: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_index_path(conn, :index))

      assert html =~ "Ailment updated successfully"
      assert html =~ "some updated description"
    end

    test "deletes ailment in listing", %{conn: conn, ailment: ailment} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_index_path(conn, :index))

      assert index_live |> element("#ailment-#{ailment.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#ailment-#{ailment.id}")
    end
  end

  describe "Show" do
    setup [:create_ailment]

    test "displays ailment", %{conn: conn, ailment: ailment} do
      {:ok, _show_live, html} = live(conn, Routes.ailment_show_path(conn, :show, ailment))

      assert html =~ "Show Ailment"
      assert html =~ ailment.description
    end

    test "updates ailment within modal", %{conn: conn, ailment: ailment} do
      {:ok, show_live, _html} = live(conn, Routes.ailment_show_path(conn, :show, ailment))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit Ailment"

      assert_patch(show_live, Routes.ailment_show_path(conn, :edit, ailment))

      assert show_live
             |> form("#ailment-form", ailment: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#ailment-form", ailment: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_show_path(conn, :show, ailment))

      assert html =~ "Ailment updated successfully"
      assert html =~ "some updated description"
    end
  end
end
